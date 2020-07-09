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
		assertEquals("konnpyu-ta",text.kana2rome("こんぴゅーた"));
		assertEquals("firumu",text.kana2rome("ふぃるむ"));
		assertEquals("teiramisu",text.kana2rome("てぃらみす"));
		assertEquals("kurikku",text.kana2rome("くりっく"));
	}
	
	@Test
	void 全角だったらTrueを返す() {
		assertEquals(true,Text.isZenkaku('あ'));
	}
	
	@Test
	void 半角だったらFalseを返す() {
		assertEquals(false,Text.isZenkaku('a'));
	}
	
	@Test
	void 文字の長さを判定する() {
		assertEquals(1,Text.countStringLength("a"));
		assertEquals(2,Text.countStringLength("あ"));
		assertEquals(3,Text.countStringLength("aあ"));
		assertEquals(9,Text.countStringLength("あいうabc"));		
	}

}
