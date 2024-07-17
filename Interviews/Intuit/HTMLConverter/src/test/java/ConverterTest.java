import org.junit.Assert;
import org.junit.Test;

public class ConverterTest {

    @Test
    public void testHeadingConversion() {
        String markdown = "# Heading 1\n## Heading 2\n### Heading 3\n";
        String expectedHtml = "<h1>Heading 1</h1>\n<h2>Heading 2</h2>\n<h3>Heading 3</h3>\n";
        String actualHtml = Converter.markdownToHtml(markdown);
        Assert.assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testParagraphConversion() {
        String markdown = "This is a paragraph.\n\nAnother paragraph here.";
        String expectedHtml = "<p>This is a paragraph.</p>\n<p>Another paragraph here.</p>\n";
        String actualHtml = Converter.markdownToHtml(markdown);
        Assert.assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testLinkConversion() {
        String markdown = "Check out [Google](http://www.google.com) and [Yahoo](http://www.yahoo.com)";
        String expectedHtml = "<p>Check out <a href=\"http://www.google.com\">Google</a> and <a href=\"http://www.yahoo.com\">Yahoo</a></p>\n";
        String actualHtml = Converter.markdownToHtml(markdown);
        Assert.assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testMixedContentConversion() {
        String markdown = "# Header\nThis is a [link](http://example.com).\n\nAnother paragraph.";
        String expectedHtml = "<h1>Header</h1>\n<p>This is a <a href=\"http://example.com\">link</a>.</p>\n<p>Another paragraph.</p>\n";
        String actualHtml = Converter.markdownToHtml(markdown);
        Assert.assertEquals(expectedHtml, actualHtml);
    }
}
