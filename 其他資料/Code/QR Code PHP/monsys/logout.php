<?php session_start(); ?>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<?php
		setcookie("id",NULL);
		setcookie("passed","FALSE");	
echo '登出中......';
echo '<meta http-equiv=REFRESH CONTENT=1;url=adminmain.php>';
?>