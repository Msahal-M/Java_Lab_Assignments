import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

df = pd.read_csv('polynomial_regression_dataset.csv')
data = np.array(df)

x = data[:, 0:4]
y = data[:, 4]

x_test = x[5000:, :]
y_test = y[5000:,]

# plt.scatter(x_test[:, 2], y_test)
# plt.show()


