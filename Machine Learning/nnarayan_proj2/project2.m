W = train_lr();
[ errRate, reciprocalRank, y ] = test_lr();

fprintf('My ubit name is %s\n','nnarayan');
fprintf('My student number is %d \n',50134647);

fprintf('Error rate for Logistic regression is %4.2f\n',errRate);

fprintf('Reciprocal rank for Logistic regression is %4.2f\n',reciprocalRank);