import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TextTest {

	@Test
	void 指定箇所と文字が一致しているならtrue() {
		assertEquals(true,Text.checkText("abcd",'a',0));
		assertEquals(true,Text.checkText("abcd",'b',1));
		assertEquals(true,Text.checkText("abcd",'c',2));
		assertEquals(true,Text.checkText("abcd",'d',3));
	}
	
	@Test
	void 指定箇所と文字が一致していないならfalse() {
		assertEquals(false,Text.checkText("abcd",'b',0));
		assertEquals(false,Text.checkText("abcd",'c',1));
		assertEquals(false,Text.checkText("abcd",'d',2));
		assertEquals(false,Text.checkText("abcd",'a',3));
	}
	
	@Test
	void 文字列が一致しているならtrue() {
		assertEquals(true,Text.matchText("abcd","abcd"));
	}
	
	@Test
	void 文字列が一致していないならfalse() {
		assertEquals(false,Text.matchText("abcd","efgh"));
	}
	
	
	@Test
	void 文字列をローマ字に変換する() {
		Text text = new Text();
		assertEquals("fairu",text.kana2rome("ふぁいる"));
		assertEquals("firumu",text.kana2rome("ふぃるむ"));
		assertEquals("teiramisu",text.kana2rome("てぃらみす"));
	}

}
