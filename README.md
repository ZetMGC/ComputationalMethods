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
```java
LinearEquationSystem les = Reader.readEquations("src/Gauss/equations.txt");
double[][] A = les.getMatrixA();
double[] B = les.getVectorB();
les.setSolution(solve(A, B));
```
> v2.1
