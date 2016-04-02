<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-37458318-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<?php
	$File_Extension = explode(".", $_FILES['myfile']['name']);
	$File_Extension = $File_Extension[count($File_Extension)-1]; 
	if($File_Extension!=jpeg&&$File_Extension!=jpg&&$File_Extension!=gif&&$File_Extension!=png){
		echo "<script> alert('不符合所規定的檔案格式!'); location.href='monster_insert.php'; </script>";
	}
	else if($_FILES["file"]["error"])
		echo "<script> alert('請檢查檔案符合規定!'); location.href='monster_insert.php'; </script>";
	else if(file_exists("image/" . $_FILES["myfile"]["name"])){
		echo "<script> alert('已有相同名稱檔案，請重新命名圖片!'); location.href='monster_insert.php'; </script>";
	}
	else{
	$upload_dir="./image/";
	$upload_file=$upload_dir . $_FILES["myfile"]["name"];
	if(move_uploaded_file($_FILES["myfile"]["tmp_name"],$upload_file)){
		echo"<STRONG>圖片上傳成功</STRONG>";
	
$image=	        "./image/{$_FILES['myfile']['name']}";
$monster_name = $_POST["monster_name"];
$level=		$_POST["level"];
$hp=		$_POST["hp"];
$atk=		$_POST["atk"];
$def=		$_POST["def"];
$exp=		$_POST["exp"];

		$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
		mysql_query("SET NAMES 'UTF8'");
		if(! mysql_select_db("a5")) die("無法讀取資料庫");
		
$sql =
"INSERT INTO monster_data(image,monster_name,level,hp,atk,def,exp)
VALUES ('$image','$monster_name','$level','$hp','$atk','$def','$exp');
";
$back_page = "right.html";

if(mysql_query($sql))
{ 
	echo "<script> alert('新增成功!'); location.href='$back_page'; </script>";
}
else
{   
	echo "<script> alert('新增失敗'); location.href='$back_page'; </script>";
}
}}
?>