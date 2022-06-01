var  p_dom=document.getElementById('province');
var  c_dom=document.getElementById('city');
var provinceArr=['湖北省','山西省','四川省'];	
var cityArr=[];
cityArr[0]=['湖北省','武汉市','潜江市','随州市','荆州市'];
cityArr[1]=['山西省','太原市','运城市','大同市'];
cityArr[2]=['四川省','成都市','攀枝花市','自贡市'];
function show(){
	for(var i=0;i<provinceArr.length;i++){
		var option=new Option(provinceArr[i],provinceArr[i]);
		p_dom.add(option);
	}
}
document.onready=show();
function city_change(p_dom,c_dom,cityArr){
	c_dom.length=0;
	//console.log(p_dom.value);
	for(var i=0;i<cityArr.length;i++){
		if(p_dom.value==cityArr[i][0]){
			//console.log(p_dom.value);
			var index=0
			for(var j=0;j<i;j++){
				index = index+cityArr[j].length-1;
			}
			for(var k=1;k<cityArr[i].length;k++){
				var option=new Option(cityArr[i][k],index+k);
				c_dom.add(option);
			}
		}
	}
}
p_dom.onchange=function(){city_change(p_dom,c_dom,cityArr)};
//执行一个laydate实例
laydate.render({
  elem: '#birthday' //指定元素
});
$('#username').blur(function(){
	/* var schoolList={
			  "pageCount": "2",
			  "pageIndex": "1",
			  "sign": "1",
			  "uniPayId": "9dc1308bab2b4b49987a0c12dd7339f5"
			};*/
	var re = new RegExp("^\\w+$");
	var re2 = new RegExp('\\w{6,30}');
	var value=$(this).val();
	if(!re.test(value)){
		$('#dlm').css({color:'#ff0000'}).html("用户名不能为空");
		if(value.length!=0)
			{
				$('#dlm').css({color:'#ff0000'}).html("用户名必须由字母、数字或“_”组成");
			}
		
	}else if(!re2.test(value)){
		$('#dlm').css({color:'#ff0000'}).html("用户名长度不少于6位，不多于30位");
	}else{
		 $.ajax({
		        type:"POST",
		        url:"/My12306/querybyname",
		  //      contentType: "application/json", //必须这样写
		   //     dataType:"json",
		        data:"username="+value,//schoolList是你要提交是json字符串
		        success:function (result) {
		        	if(result=='true')
		        		$('#dlm').css({color:'#ff0000'}).html("用户名已被注册");
		        	else
		        		$('#dlm').html("<img style='width:20px;height:20px' src='/My12306/images/duigou.jpg' />");
		        }
		 
		    });
		
		
		
	}
});

