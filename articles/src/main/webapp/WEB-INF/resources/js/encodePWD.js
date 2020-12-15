function encodeUserPwd(){
	var check = "";
	var val = document.getElementById('login_form:user_password').value;;
	
	check = sha256(val);
	
	document.getElementById('login_form:user_password').value = check;
	
	
	return true;
	
}