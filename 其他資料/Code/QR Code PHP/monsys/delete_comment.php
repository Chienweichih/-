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

$con = mysql_connect("localhost","A5","5149") or die("�L�k�P��Ʈw���A���إ߳s�u");
if(! mysql_select_db("a5")) die("�L�kŪ����Ʈw");
mysql_query("DELETE FROM comment WHERE comment_id=$_GET[id]");

mysql_close($con);

$url = "comment_del.php";
echo "<script type='text/javascript'>";
echo "window.location.href='$url'";
echo "</script>"; 
?> 