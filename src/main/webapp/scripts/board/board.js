var BoardUtil = {
	vo:{}
};

var BaordRenderUtil = {
	render:function(seq) {
		$.ajax({
			url:"/board/data/json",
			type:"get",
			data:{'seq':seq},
			dataType:"text",
			success:function(data) {
				var vo = $.parseJSON(data);
				$("#divContentWrap").html($("#contentTemplate").tmpl(vo));
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
	,renderList:function(vo) {
		$.ajax({
			url:"/board/list/json",
			type:"get",
			data:vo,
			dataType:"text",
			success:function(data) {
				var list = $.parseJSON(data);
				if(list.length > 0) {
					$("#divContentList").html($("#contentTemplate").tmpl(list));
				} else {
					$("#divContentList").html($("#emptyContentTemplate").tmpl());
				}
			},
			error:function(error) {
				console.log( error.status + ":" +error.statusText );
			}
		});
	}
};

var BoardDeleteUtil = {
	proc:function(seq) {
		if(confirm('정말 삭제하시겠습니까?')) {
			$.ajax({
				url:"/board/delete/proc",
				type:"post",
				data:{'seq':seq},
				dataType:"text",
				success:function(data) {
					if(data === 'success') {
						alert('삭제되었습니다.');
					} else {
						alert('실패하였습니다.');
					}
					BaordRenderUtil.renderList(BoardUtil.vo);
				},
				error:function(error) {
					console.log( error.status + ":" +error.statusText );
				}
			});
		}
	}
};