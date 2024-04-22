# Computational Methods
### What does it include?
The project on **computational methods**, which currently includes: 
1. Gauss Method
2. Conjugate Gradient Method 
3. Local Smoothing Method
   - Gaussian noise method
   - The least squares method

## Examples

### Gauss
**Gauss Method**
1. A system of linear equations is being created.
2. Separate variables are created for the matrix of values and the vector.
3. The ``` solve ``` method is called and its result is recorded in the ``` solution ``` field
```java
LinearEquationSystem les = Reader.readEquations("src/Gauss/equations.txt");
double[][] A = les.getMatrixA();
double[] B = les.getVectorB();
les.setSolution(solve(A, B));
```
> v2.1
