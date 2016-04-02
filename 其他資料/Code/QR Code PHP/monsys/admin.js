// JavaScript Document
lastScrollY=0;
function heartBeat(){
var diffY;
if (document.documentElement && document.documentElement.scrollTop)
    diffY = document.documentElement.scrollTop;
else if (document.body)
    diffY = document.body.scrollTop
else
    {/*Netscape stuff*/}
    
//alert(diffY);
percent=.1*(diffY-lastScrollY);
if(percent>0)percent=Math.ceil(percent);
else percent=Math.floor(percent);
document.getElementById("lovexin12").style.top=parseInt(document.getElementById("lovexin12").style.top)+percent+"px";


lastScrollY=lastScrollY+percent;
//alert(lastScrollY);
}
suspendcode12="<DIV id=\"lovexin12\" style='right:22px;POSITION:absolute;TOP:500px;RIGHT:0px;'><a href=adminmain.php onclick=\"lovexin12.style.visibility='hidden'\">返回管理頁</a></div>"



document.write(suspendcode12);

window.setInterval("heartBeat()",1);