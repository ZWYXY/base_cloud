package ogr.spring.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class Searcher {

    public static void search(String indexDir, String q) throws IOException, ParseException {
        // 获取要查询的路径，也就是索引所在位置
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        IndexReader reader = DirectoryReader.open(dir);

        // 构建indexSearcher
        IndexSearcher searcher = new IndexSearcher(reader);
        // 标准分词器
        Analyzer analyzer = new StandardAnalyzer();
        // 查询解析器
        QueryParser parser = new QueryParser("contents", analyzer);
        // 通过解析要查询的String，获取查询对象，q为传进来的待查字符串
        Query query  = parser.parse(q);

        // 记录索引开始时间
        long startTime = System.currentTimeMillis();
        // 开始查询， 查询前10条数据，将记录保存在docs中
        TopDocs docs = searcher.search(query, 10);
        // 记录索引结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("匹配" + q + "共耗时" + (endTime-startTime) + "毫秒");
        System.out.println("查询到" + docs.totalHits + "条记录");

        // 取出每条查询结果
        for(ScoreDoc scoreDoc : docs.scoreDocs) {
            // scoreDoc.doc 相当于docID，根据这个docID获取文档
            Document doc = searcher.doc(scoreDoc.doc);
            System.err.println(doc.get("fullPath"));
        }
        reader.close();
    }

    public static void main(String[] args) {
        String indexDir = "D:\\Lucene\\indexDir";
        String q = "Tomcat";
        try {
            search(indexDir, q);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
