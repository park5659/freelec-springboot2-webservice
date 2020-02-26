var main = {             //main이라는 scope를 지정한 이유는 여러 사람이 참여하는 프로젝트에서 중복된 함수이름이 발생하는걸 방지하기위해서
    init : function () {   //이걸 작성후 머스테치 파일이 쓸수 있도록 footer.mustache에 추가해줌.
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {  //btn-update란 id를 가진 html 엘리먼트에 click이벤트 발생할때 update function을 실행하도록 이벤트 등록함.
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/Posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)  //JSON.stringify()는 자바스크립트값이나 객체를 JSON문자열로 변환함.
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';  //글등록이 성공하면 메인페이지('/')로 이동함.
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {  //신규로 추가될 update function임.
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',  //PostsApiController에 @PutMapping으로 선언했기때문에 PUT을 사용함. 참고로 REST에서 CRUD는 생성-POST, 읽기-GET, 수정-PUT, 삭제-DELETE 사용.
            url: '/api/v1/posts/'+id,  //어느게시글을 수정할지 URL Path로 구분하기 위해 Path에 id를 추가함.
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();