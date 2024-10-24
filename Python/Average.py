//Average Best of two test Marks 
test1=float(input("Enter the marks of test1: "))
test2=float(input("Enter the marks of test2: "))
test3=float(input("Enter the marks of test3: "))
if test1<=test2 and test1<=test3:
    min_marks=test1
elif test2<=test1 and test2<=test3:
    min_marks=test2
else:
    minmarks=test3
average=((test1+test2+test3)-min_marks)/2
print("The Average Best of two Test: ",average)
