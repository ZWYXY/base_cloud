package ogr.spring.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class Indexer {

    private IndexWriter writer;

    /**
     * 构造方法，实例化IndexWriter
     * @param indexDir
     * @throws IOException
     */
    public Indexer(String indexDir) throws IOException {
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        // 标准分词器，会自动去掉空格 is a the 等
        Analyzer analyzer = new StandardAnalyzer();
        // 将标准分词器配到写索引的配置中
        IndexWriterConfig  config = new IndexWriterConfig(analyzer);
        // 实例化写索引对象
        writer = new IndexWriter(dir, config);
    }
}
