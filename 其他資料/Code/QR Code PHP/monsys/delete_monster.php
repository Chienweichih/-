<style type="text/css">
<!--
body{
	background-image: url(suse_zhiwen-033.jpg);
}
-->
</style>
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

$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
if(! mysql_select_db("a5")) die("無法讀取資料庫");
mysql_query("DELETE FROM monster_data WHERE monster_id=$_GET[id]");
mysql_query("DELETE FROM treasure_drop WHERE monster_id=$_GET[id]");
mysql_query("DELETE FROM comment WHERE monster_id=$_GET[id]");

mysql_close($con);;

  
$url = "delete_monster_page.php?";
echo "<script type='text/javascript'>";
echo "window.location.href='$url'";
echo "</script>"; 
?> 


