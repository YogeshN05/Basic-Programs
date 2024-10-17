#include<stdio.h>
#include<math.h>

int main()
{
    int s1;
    int s2;
    int s3;
    double Hypotenus;

    printf("\nEnter the value of Side1: ");
    scanf("%d",&s1);

    printf("Value of Side1: %d",s1);

    printf("\n\nEnter the value of Side2: ");
    scanf("%d",&s2);

    printf("Value of Side2: %d",s2);

    s3=(s1*s1)+(s2*s2);
    Hypotenus=sqrt(s3);

    printf("\n\nHypotenus of a Triangle: %lf\n\n",Hypotenus);

    return 0;
}
