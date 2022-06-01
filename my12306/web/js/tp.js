/**
 * 
 * 
 * 
 * 
 */
//取消浏览器默认拖放时间
$(document).on({
    'dragleave':function(){return false;},
    'dragdrop' :function(){return false;},
    'dragenter' :function(){return false;},
    'dragover' :function(){return false;},
});
$(function(){
    var file_box = document.getElementById('tp');
    file_box.addEventListener('drop',function(e){
        //获取时间对应上传文件集合列表
    var fileList=e.dataTransfer.files;
        //获取文件集合列表的第一个文件
    var file_one=fileList[0];
        //获取这个文件的名字
    var file_name=file_one.name;
        //获取文件的扩展名
    var file_name_ext=file_name.substring(file_name.lastIndexOf('.'));
    if(file_name_ext=='.jpg'||file_name_ext=='.png'){
        $("#tp").css({'background':'#24b300'});
        $('#button').click(function(e){
            
            var beizhu = $('content').val();
            var lklx=$('#passengerType').val();
            var formdata=new FormData();
            formdata.append('wjdx',file_one);
            formdata.append('lklx',lklx);
            formdata.append('beizhu',beizhu);

            e.preventDefault();
            var beizhu=$('#content').val();
            console.log(beizhu);
        });
    }
    
});
});