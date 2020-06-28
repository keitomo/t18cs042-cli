import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TextTest {

	@Test
	void 文字が一致していたらtrue() {
		assertEquals(true,Text.CheckText("abcd",'a',0));
		assertEquals(true,Text.CheckText("abcd",'b',1));
		assertEquals(true,Text.CheckText("abcd",'c',2));
		assertEquals(true,Text.CheckText("abcd",'d',3));
	}
	
	@Test
	void 文字が一致していなかったらfalse() {
		assertEquals(false,Text.CheckText("abcd",'b',0));
		assertEquals(false,Text.CheckText("abcd",'c',1));
		assertEquals(false,Text.CheckText("abcd",'d',2));
		assertEquals(false,Text.CheckText("abcd",'a',3));
	}
	
	
	@Test
	void 文字列をローマ字に変換する() {
		Text text = new Text();
		assertEquals("fairu",text.kana2rome("ふぁいる"));
		assertEquals("firumu",text.kana2rome("ふぃるむ"));
	}

}
