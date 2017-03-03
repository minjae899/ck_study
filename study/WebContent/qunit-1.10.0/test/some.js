test("test 입니다", function(){
	ok(true, "성공");
});

asyncTest("asynct test입니다", function(){
	setTimeout(function(){
		ok(true, "성공");
		start();
	}, 100);
});

test("test 입니다", function(){
	expect(1);
	ok(true, "성공");
});

module("모듈");
test("ok test입니다", function(){
	ok(true, "성공");
});
test("fail test입니다.", function(){
	ok(false, "실패");
});

module("모듈2");
test("expect test입니다.", function(){
	expect(2);
	ok(true, "성공");
});