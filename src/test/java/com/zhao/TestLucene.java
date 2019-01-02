package com.zhao;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author aotu
 * @date 2019/1/2 14:05
 */
public class TestLucene {
    @Test
    public void test1() throws IOException {
        Directory directory = FSDirectory.open(new File("D:/index"));
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, new StandardAnalyzer(Version.LUCENE_44));
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        for (int i = 0; i < 10; i++) {
            Document document = new Document();
            document.add(new StringField("id", String.valueOf(i), Field.Store.YES));
            document.add(new StringField("name", "凹凸", Field.Store.YES));
            document.add(new TextField("content", i + "这是内容，测试使用", Field.Store.YES));
            indexWriter.addDocument(document);

        }
        indexWriter.commit();
        indexWriter.close();

    }


    @Test
    public void test2() throws IOException {
        Directory directory = FSDirectory.open(new File("D:/index"));
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        TopDocs topDocs = indexSearcher.search(new TermQuery(new Term("content", "是")), 100);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (int i = 0; i < scoreDocs.length; i++) {
            Document document = indexSearcher.doc(scoreDocs[i].doc);
            System.out.println(document.get("id"));
            System.out.println("分数" + scoreDocs[i].score);
            System.out.println("name:" + document.get("name"));
            System.out.println("content:" + document.get("content"));
            System.out.println("----------------------------");
        }
    }


    @Test
    public void test3() throws IOException {
        Directory directory = FSDirectory.open(new File("D:/index"));
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, new StandardAnalyzer(Version.LUCENE_44));
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        indexWriter.deleteAll();
        indexWriter.commit();
        indexWriter.close();
    }

}
