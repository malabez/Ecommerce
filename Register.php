<?php
         $con=mysqli_connect("mysql1.000webhost.com","a8487083_new","passsword","a8487083_databas");
 if (!$con) {
   echo err; die('Connect Error: ' . mysqli_connect_error());
}
    
    $name = $_POST["name"];
    $age = $_POST["age"];
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "INSERT INTO USer (`name`, `age`, `username`, `password`) VALUES (?, ?, ?, ?)");
  if($statement){  
mysqli_stmt_bind_param($statement, "siss", $name, $age, $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_close($statement);
}
else{die("err2");}    
    mysqli_close($con);
?>
