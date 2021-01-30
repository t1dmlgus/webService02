/**
 * Created by jojoldu@gmail.com on 2018. 1. 3.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */


var main = {
    init : function () {

        var _this = this;

        $('#btn-save').on('click', function () {
            alert("저장됨");
            _this.save();
        }),

        $('#btn-update').on('click',function(){
            alert("수정됨");
            _this.update();
        }),

        $('#btn-delete').on('click',function(){
            alert("삭제됨");
            _this.delete();
        })

    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()

        };
            alert($('#title').val());
            alert($('#author').val());
            alert($('#content').val());

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href='/';
        }).fail(function (error) {
            alert(error);
        });
    },
    update : function () {
              var data = {
                  title: $('#title').val(),
                  content: $('#content').val()

              };
                  alert($('#title').val());
                  alert($('#content').val());

              var id = $('#id').val();


              $.ajax({
                  type: 'PUT',
                  url: '/api/v1/posts/'+id,
                  dataType: 'json',
                  contentType:'application/json; charset=utf-8',
                  data: JSON.stringify(data)
              }).done(function() {
                  alert('글이 수정되었습니다.');
                  window.location.href='/';
              }).fail(function (error) {
                  alert(error);
              });
    },
    delete : function(){

        var id = $('#id').val();


        $.ajax({

            type: 'DELETE',
            url : '/api/v1/posts/' +id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'

        }).done(function(){

            alert('글이 삭제되었습니다.');
            window.location.href ='/';

        }).fail(function(error){

            alert(JSON.stringify(error));
        });

    }
};

main.init();