<?php
/*********************************************************************************
*                                                                                *
*	DESCRIPTION:     This form is used to connect from with the database         *
*				                                                                 *
* 	CREATED:         16 May, 2016                                                *
* 	AUTHOR/S:        shivendra                                                   *
*                                                                                *
*   (cc) WeaveBytes InfoTech Pvt Ltd, www.weavebytes.com                         *
*                                                                                *
**********************************************************************************/
	define('HOST','localhost');
	define('USER','root');
	define('PASSWORD', '123');
	define('DBNAME','LoginDemo');

	//conencting to database
	$con=mysql_connect(HOST, USER, PASSWORD);
	if(!$con){
		echo"connection not created";
	}
	
	//selecting database
	$db=mysql_select_db(DBNAME);
		
	if(!$db){
		die("<b>ERROR: Failed to connect to database. <br>Can't continure further...</b>");
	}
?>
