<?php
    $con=mysqli_connect("mysql1.000webhost.com","a8487083_new","password","a8487083_databas");
  if (!$con) {
   echo err; die('Connect Error: ' . mysqli_connect_error());
}
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM USer WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $name, $age, $username, $password);
    
    $user = array();
    
    while(mysqli_stmt_fetch($statement)){
        $user["name"] = $name;
        $user["age"] = $age;
        $user["username"] = $username;
        $user["password"] = $password;
    }
    
    echo json_encode($user);
    mysqli_close($con);
?>
