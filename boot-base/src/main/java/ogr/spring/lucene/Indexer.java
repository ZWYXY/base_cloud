package ogr.spring.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

// 建立索引
public class Indexer {

    /**
     * 写 索引实例
     */
    private static IndexWriter writer;

    /**
     * 构造方法，实例化IndexWriter
     *
     * @param indexDir
     * @throws IOException
     */
    public Indexer(String indexDir) throws IOException {
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        // 标准分词器，会自动去掉空格 is a the 等
        Analyzer analyzer = new StandardAnalyzer();
        // 将标准分词器配到写索引的配置中
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        // 实例化写索引对象
        writer = new IndexWriter(dir, config);
    }

    /**
     * 索引指定目录下的所有文件
     */
    public int indexAll(String dataDir) throws IOException {
        File[] files = new File(dataDir).listFiles();
        if (null != files) {
            for (File file : files) {
                // 调用indexFile，对每个文件进行索引
                indexFile(file);
            }
        }
        // 返回索引的文件数
        return writer.numRamDocs();
    }

    /**
     * 索引指定的文件
     *
     * @param file
     * @throws IOException
     */
    private void indexFile(File file) throws IOException {
        System.err.println("索引文件路径：" + file.getCanonicalPath());
        // 获取文件的document
        Document doc = getDocument(file);
        writer.addDocument(doc);
    }

    private Document getDocument(File file) throws IOException {
        Document doc = new Document();
        // 添加开始字段
        // 添加内容
        doc.add(new TextField("contents", new FileReader(file)));
        // 添加文件名
        doc.add(new TextField("fileName", file.getName(), Field.Store.YES));
        // 添加文件路径
        doc.add(new TextField("fullPath", file.getCanonicalPath(), Field.Store.YES));
        return doc;
    }

    public static void main(String[] args) {

        // 索引存放路径
        String indexDir = "D:\\Lucene\\indexDir";
        // 文件存放路径
        String dataDir = "D:\\Lucene\\data";

        int indexedNum = 0;
        long startTime = System.currentTimeMillis();
        try {
            Indexer indexer = new Indexer(indexDir);
            indexedNum = indexer.indexAll(dataDir);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.commit();
                if(null != writer) {
                    writer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("索引耗时" + (endTime - startTime) + "毫秒");
        System.out.println("共索引了" + indexedNum + "个文件");
    }


}














