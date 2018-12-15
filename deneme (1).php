<?php

if($_SERVER['REQUEST_METHOD']=="PUT"){
//	$ad= @$_POST['ad'];
//	$yas= @$_POST['yas'];
    $ad="Mehmet can";
    $yas="24";
	

	
	
	parse_str(file_get_contents("php://input"),$obj);
    $ad =  $obj['ad'];

	
   
    
	
}

function init(){
     global $ad, $yas;

		$user = array();
		$user[0] = array("ad"=>$ad, "yas"=>$yas);
	
		return $user;
	}


   echo json_encode(init());


?>