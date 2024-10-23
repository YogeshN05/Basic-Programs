#include<stdio.h>
int main()
{
    int cal;
    int a,b;
    printf("1.Addition\n2.Subraction\n3.Multiplication\n4.Division\n5.Modulus\n\nEnter your choice: ");
    scanf("%d",&cal);
    switch(cal)
    {
    case 1: {
        printf("\nEnter the Value of a: ");
        scanf("%d",&a);
        printf("\nEnter the Value of b: ");
        scanf("%d",&b);
        int sum=a+b;
        printf("\nAddtion of Two numbers is %d",sum);
        break;
    }
    case 2: {
        printf("Enter the Value of a: ");
        scanf("%d",&a);
        printf("\nEnter the Value of b: ");
        scanf("%d",&b);
        int diff=a-b;
        printf("\nSubtraction of Two numbers is %d",diff);
        break;
    }
    case 3: {
        printf("Enter the Value of a: ");
        scanf("%d",&a);
        printf("\nEnter the Value of b: ");
        scanf("%d",&b);
        int mult=a*b;
        printf("\nMultiplication of Two numbers is %d",mult);
        break;
    }
    case 4: {
        printf("Enter the Value of a: ");
        scanf("%d",&a);
        printf("\nEnter the Value of b: ");
        scanf("%d",&b);
        float div=a/(float)b;
        printf("\nDivision of Two numbers is %f",div);
        break;
    }
    case 5: {
        printf("\nEnter the Value of a: ");
        scanf("%d",&a);
        printf("\nEnter the Value of b: ");
        scanf("%d",&b);
        int mod=a%b;
        printf("\nModulus of Two numbers is %d",mod);
        break;
    }
    default:
        printf("Enter a valid inputs");

    }
}
