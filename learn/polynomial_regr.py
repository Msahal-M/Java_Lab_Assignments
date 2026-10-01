import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D


df = pd.read_csv('polynomial_regression_dataset.csv')
data = np.array(df)

x = data[:5000,0:4]
x_test = data[5000:, 0:4]
y = data[:5000,4]
y_test = data[5000:, 4]

m, n = x.shape
m_test, n_test = x_test.shape

mean_x3 = np.nanmean(x[:, 2])

x[np.isnan(x[:, 2]), 2] = mean_x3


x_train = x.copy()

x_train[:, 0] = x_train[:, 0] / 5
x_train[:, 1] = x_train[:, 1] / 3
x_train[:, 2] = x_train[:, 2] / 100
x_train[:, 3] = x_train[:, 3] / 7

def linear_regr(w1, w2, w3, w4, b, x):
    return w1 * x[:, 0] + w2 * x[:, 1] + w3 * (x[:, 2] ** 0) + w4 * x[:, 3] + b

def gradient_descent(w1, w2, w3, w4, b, x, y, alpha, m):
    for _ in range(50000):
        dw1 = (1 / m) * np.sum(((w1 * x[:, 0] + w2 * x[:, 1] + w3 * x[:, 2] + w4 * x[:, 3] + b) - y) * x[:, 0])
        dw2 = (1 / m) * np.sum(((w1 * x[:, 0] + w2 * x[:, 1] + w3 * x[:, 2] + w4 * x[:, 3] + b) - y) * x[:, 1])
        dw3 = (1 / m) * np.sum(((w1 * x[:, 0] + w2 * x[:, 1] + w3 * x[:, 2] + w4 * x[:, 3] + b) - y) * x[:, 2])
        dw4 = (1 / m) * np.sum(((w1 * x[:, 0] + w2 * x[:, 1] + w3 * x[:, 2] + w4 * x[:, 3] + b) - y) * x[:, 3])

        db = (1 / m) * np.sum((w1 * x[:, 0] + w2 * x[:, 1] + w3 * x[:, 2] + w4 * x[:, 3] + b) - y)
        old_w1 = w1
        old_w2 = w2
        old_w3 = w3
        old_w4 = w4
        old_b = b

        w1 = w1 - alpha * dw1
        w2 = w2 - alpha * dw2
        w3 = w3 - alpha * dw3
        w4 = w4 - alpha * dw4
        b = b - alpha * db

        if (abs(old_w1 - w1)) <= 0.0001 and (abs(old_w2 - w2)) <= 0.0001 and (abs(old_w3 - w3)) <= 0.0001 and (abs(old_w4 - w4)) <= 0.0001 and (abs(old_b - b)) <= 0.0001:
            break
 
    return w1 / 5, w2 / 3, w3 / 100, w4 / 7, b


def cost(f, y, m):
    return round((1 / (2 * m)) * np.sum((f - y)**2), 2)



w1, w2, w3, w4, b = gradient_descent(10, 12, 2, 9, 10, x_train, y, 0.01, m)
f = linear_regr(w1, w2, w3, w4, b, x)
acc = cost(f, y, m)

print(f"w1 = {w1}, w2 = {w2}, w3 = {w3}, w4 = {w4}, b = {b},  cost = {acc}")

print(f[3])
print(f[100])

f_test = linear_regr(w1, w2, w3, w4, b, x_test)
costt = cost(f_test, y_test, m_test)

print(f" \n COST = {costt}")

print(f_test[2])


r2_train = 1 - (np.sum((y - f)**2) / np.sum((y - np.mean(y))**2))

r2_test = 1 - (np.sum((y_test - f_test)**2) / np.sum((y_test - np.mean(y_test))**2))

print(f" r2_train = {r2_train},   r2_test = {r2_test}")



plt.scatter(x_test[:, 0], y_test)
plt.scatter(x_test[:, 0], f_test, color='red')
plt.xlabel("x1")
plt.ylabel("y")
plt.show()

plt.scatter(x_test[:, 1], y_test)
plt.scatter(x_test[:, 1], f_test, color='red')
plt.xlabel("x2")
plt.ylabel("y")
plt.show()

plt.scatter(x_test[:, 2], y_test)
plt.scatter(x_test[:, 2], f_test, color='red')
plt.xlabel("x3")
plt.ylabel("y")
plt.show()

plt.scatter(x_test[:, 3], y_test)
plt.scatter(x_test[:, 3], f_test, color='red')
plt.xlabel("x4")
plt.ylabel("y")
plt.show()