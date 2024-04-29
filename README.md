# Computational Methods
### What does it include?
The project on **computational methods**, which currently includes: 
1. Gauss Method
2. Conjugate Gradient Method 
3. Local Smoothing Method
   - Gaussian noise method
   - The least squares method
   - The ```GraphHandler``` class allows you to output a graph of a function, noise, and a smoothed graph.
4. A method for numerical integration of a function over a given interval using quadrature formulas.
5. Adams Method

## Examples

### Gauss
**Gauss Method**
1. A system of linear equations is being created.
2. Separate variables are created for the matrix of values and the vector.
3. The ``` solve ``` method is called and its result is saved in the ``` solution ``` field
```java
LinearEquationSystem les = Reader.readEquations("src/Gauss/equations.txt");
double[][] A = les.getMatrixA();
double[] B = les.getVectorB();
les.setSolution(solve(A, B));
```
**Gauss Noise**

Allows you to add Gaussian noise to the original data.
```java
SmoothingParameters smoothingParameters = Reader.readDataSmoothing("src/Smoothing/input1.txt");
double[] noisyData = GaussNoise.addGaussianNoise(smoothingParameters.y, 0.0, 2);
```

**Gauss Quadrature**

A method for calculating integration nodes and corresponding weights for the Gaussian quadrature method on each of the sub-sections of the partition of the initial integration interval [a, b].
```java
double[] nodes = new double[n * s];
double[] weights = new double[n * s];
GaussQuadrature.NodesCalculation(weights, nodes, a, b, n, s);
```


> v5.1
