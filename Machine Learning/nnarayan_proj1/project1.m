function[] = project1()

[MOptimal1, LambdaOptimal1] = train_cfs();

load W_cfs ;
load mu_cfs ;
load s_cfs ;

M_cfs = MOptimal1;

lambda_cfs = LambdaOptimal1 ;

rms_cfs = test_cfs(WOptimal1, MOptimal1, LambdaOptimal1, SOptimal1);

[ M_gd, lambda_gd, WOpt ] = train_gd( SOptimal1 , MOptimal1);

rms_gd = test_gd( WOpt ,M_gd , lambda_gd, SOptimal1 );

fprintf('My ubit name is %s\n','nnarayan');
fprintf('My student number is %d \n',50134647);

fprintf('the model complexity M_cfs is %d\n', M_cfs);

fprintf('the regularization parameters lambda_cfs is %4.2f\n', lambda_cfs);

fprintf('the root mean square error for the closed form solution is %4.2f\n', rms_cfs);

fprintf('the model complexity M_gd is %d\n', M_gd);

fprintf('the regularization parameters lambda_gd is %4.2f\n', lambda_gd);

fprintf('the root mean square error for the gradient descent method is %4.2f\n', rms_gd);

%---------------------------------------------------------------------------------------------

end

