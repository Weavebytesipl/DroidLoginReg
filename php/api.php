<?php

// database.config.php" file connects to database every time
include("database.config.php");	


//////////////////////////////////////////////////////
//                                                  //
//     Api for new user registration                //
//                                                  //
//////////////////////////////////////////////////////

function handleRegister(){

	$ret = array('op' => 'register', 'msg' => 'Registration Successful', 'error_code' => '0');

	$username = $_POST['username'];
	$pass     = $_POST['password'];
	$email    = $_POST['email'];
	$dob      = $_POST['dob'];
	$gender   = $_POST['gender'];
	$password = md5($pass . $username);

	// ensuring username and password
	if (empty($_POST['username']) || empty($_POST['password'])) {

			// creating some data that will be the JSON response
        	$ret["error_code"] = 1;
       		$ret["msg"]        = "Please Enter Both a Username and Password.";
	        
			die( json_encode($ret) );
		}

	$result = mysql_query("INSERT INTO user(`username`,`password`,`email`,`dob`,`gender`) VALUES('$username', '$password', '$email', 		'$dob','$gender')");
			
	if ($result > 0){																							
		// creating some data that will be the JSON response
        echo json_encode($ret);
	} else {

		// some unknow error, fix me later !!!!
	}
}// handleRegister


//////////////////////////////////////////////////////
//                                                  //
//       Api for user login                         //
//                                                  //
//////////////////////////////////////////////////////
function handleLogin(){

	$ret = array('op' => 'login', 'msg'=> 'Login Successful', 'error_code'=> '0');

    // reading posted params
	$username = $_POST['username'];
	$pass     = $_POST['password'];
	$password = md5($pass . $username);

	$qry = "SELECT * FROM user WHERE username='$username' AND password='$password'";	
	$result = mysql_query($qry);

	if($result) {
		if(mysql_num_rows($result) > 0) {	
			$login_ok = true;
		}
	}

	if($login_ok){
        echo json_encode($ret);
	}else{
		$ret["error_code"] = 1;
	    $ret["msg"]        = "Invalid Credentials!";
	    
		die( json_encode($ret) );
	}
}// handleLogin

////////////////////////// MAIN ///////////////////////////////////////
if(!isset($_POST["op"]))  die("operation not specified");

$op = $_POST["op"];


////////////////////////////////////////////////
// API handlers								  //
////////////////////////////////////////////////

if($op == "login")      handleLogin();
if($op == "register")   handleRegister();
?>
