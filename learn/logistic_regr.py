import numpy as np
import pandas as pd

data = pd.read_csv('football_win_dataset.csv')
data["venue"] = (data["venue"] == "Home").astype(int)
data.loc[data["team_possession_pct_l5"].isna(), "team_possession_pct_l5"] = np.nanmedian(data["team_possession_pct_l5"])
data.loc[data["days_rest_opp"].isna(), "days_rest_opp"] = np.nanmedian(data["days_rest_opp"])
data = data.drop(columns=["match_id", "season", "league", "kickoff_hour", "manager_tenure_months"])
data = np.array(data)

x, y = data[:, 0:13], data[:, 13]
x_train, x_test = x[:7500,:], x[7500:, :]
y_train, y_test = y[:7500], y[7500:]
n, m = x.shape
for i in range(m):
    if i != 12:
        x[:, i] = (x[:, i] - np.min(x[:, i])) / (np.max(x[:, i]) - np.min(x[:, i]))


w = np.zeros(m)
b = 0

def prediction(p):
    pred = np.zeros(p.shape[0])
    for i in range(p.shape[0]):
        if p[i] >= 0.5:
            pred[i] = 1

    return pred

def logistic(w, x, b):
    # print("w:", np.shape(w), "x:", np.shape(x), "b:", np.shape(b))
    z = x @ w + b
    return 1 / (1 + np.exp(-z))

def gradient_descent(w, b, x, y, alpha=0.1):
    for i in range(1000):
        dw = (1 / x.shape[0]) * (x.T @ (logistic(w, x, b) - y))
        db = (1 / x.shape[0]) * np.sum(logistic(w, x, b) - y)

        old_w = w.copy()
        old_b = b

        w -= alpha * dw
        b -= alpha * db
        
        if i % 100 == 0:
            p = np.clip(logistic(w, x, b), 1e-12, 1 - 1e-12)
            loss = -np.mean(y * np.log(p) + (1 - y) * np.log(1 - p))
            print(i, round(loss, 4))
            

        if np.max(abs(old_w - w)) <= 0.001 and np.max(abs(old_b - b)) <= 0.001:
            break
    return w, b

def accuracy(f, y):
    total = len(f)
    incr = 0
    for m, n in zip(f, y):
        if m == n:
            incr += 1

    return 100 * (incr / total)

def precision(f, y):
    total = len(f)
    tp = 0
    fp = 0
    for i in range(total):
        if f[i] == y[i] == 1:
            tp += 1
        if f[i] == 1 and y[i] == 0:
            fp += 1
    print("precision = ", tp / (tp + fp))

def recall(f, y):
    total = len(f)
    tp = 0
    fn = 0
    for i in range(total):
        if f[i] == y[i] == 1:
            tp += 1
        if f[i] == 0 and y[i] == 1:
            fn += 1
    print("recall = ", tp / (tp + fn))

w, b = gradient_descent(w, b, x_train, y_train)
f = logistic(w, x_train, b)
f_test = logistic(w, x_test, b)
pred = prediction(f)
pred_test = prediction(f_test)
acc = accuracy(pred, y)
acc_test = accuracy(pred_test, y_test)
print("train = ", acc)
print("test = ", acc_test)
precision(pred, y)
precision(pred_test, y_test)
recall(pred, y)
recall(pred_test, y_test)