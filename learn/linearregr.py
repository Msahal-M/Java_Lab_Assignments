import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D


df = pd.read_csv('polynomial_regression_dataset.csv')



data = np.array(df)

mean_x3 = np.nanmean(data[:, 2])

data[np.isnan(data[:, 2]), 2] = mean_x3


data[:, 0] = (data[:, 0] - np.mean(data[:, 0])) / np.std(data[:, 0])
data[:, 1] = (data[:, 1] - np.mean(data[:, 1])) / np.std(data[:, 1])
data[:, 2] = (data[:, 2] - np.mean(data[:, 2])) / np.std(data[:, 2])
data[:, 3] = (data[:, 3] - np.mean(data[:, 3])) / np.std(data[:, 3])

x = data[:5000,0:4]
x_test = data[5000:, 0:4]
y = data[:5000,4]
y_test = data[5000:, 4]

x_train = x.copy()







x1 = x_train[:, 0]
x2 = x_train[:, 1]
x3 = x_train[:, 2]
x4 = x_train[:, 3]

def linear_regr(w1, w2, w3, w4, b, x1, x2, x3, x4):
    return w1 * x1 + w2 * x2 + w3 * x3 + w4 * x4 + b

def gradient_descent(w, b, x, y, alpha, m, lam):
    for _ in range(50000):
        f = x @ w + b
        error = f - y
        dw = (1 / m) * (x.T @ error)
        db = (1 / m) * np.sum(error)

        l1_ratio = 0.5
        # dw += (lam / m) * (l1_ratio * np.sign(w) + (1 - l1_ratio) * w) 
    
        w = w - alpha * dw
        b = b - alpha * db

        if np.max(np.abs(dw)) < 0.0001 and abs(db) < 0.0001:
            break
 
    return w, b

def cost(f, y, m):
    return round((1 / (2 * m)) * np.sum((f - y)**2), 2)

features = list()
exponents = list()
max_degree = 3
str_features = list()
for e1 in range(max_degree + 1):
    for e2 in range(max_degree + 1 - e1):
        for e3 in range(max_degree + 1 - e1 - e2):
            for e4 in range(max_degree + 1 - e1 - e2 - e3):
                if e1 == e2 == e3 == e4 == 0:
                    continue
                
                feature = ((x1 ** e1) * (x2 ** e2) * (x3 ** e3) * (x4 ** e4))
                str_feature = f"x1 ^ {e1}  *  x2 ^ {e2}  *  x3 ^ {e3}  *  x4 ^ {e4}"
                str_features.append(str_feature)
                features.append(feature)
                exponents.append((e1, e2, e3, e4))


x_poly = np.array(features).T

# check = pd.DataFrame(features)
# # exact duplicates
# check.T.duplicated()  # True for any column that's an exact copy of an earlier one

# # near-duplicates / perfectly correlated (more likely with derived polynomial features)
# corr = check.corr()
# print((corr.abs() > 0.999).sum())  # anything >1 here (besides the diagonal) is suspicious


m = len(y)
w = np.zeros(x_poly.shape[1])


b = 0


w, b = gradient_descent(
    w,
    b,
    x_poly,
    y,
    0.001,
    m,
    0.001
)

f = x_poly @ w + b
print("Training cost:", cost(f, y, m))

for m, i in enumerate(str_features):
    for n, j in enumerate(w):
        if m == n:
            print(i, "weight : ", j)



feature_test = list()
exponents_test = list()

for e1 in range(max_degree + 1):
    for e2 in range(max_degree + 1 - e1):
        for e3 in range(max_degree + 1 - e1 - e2):
            for e4 in range(max_degree + 1 - e1 - e2 - e3):
                if e1 == e2 == e3 == e4 == 0:
                    continue
                
                feature = ((x_test[:, 0] ** e1) * (x_test[:, 1] ** e2) * (x_test[:, 2] ** e3) * (x_test[:, 3] ** e4))
                
                feature_test.append(feature)
                exponents_test.append((e1, e2, e3, e4))

x_poly_test = np.array(feature_test).T
m_test = len(y_test)
f_test = x_poly_test @ w + b
print("Testing cost:", cost(f_test, y_test, m_test))




