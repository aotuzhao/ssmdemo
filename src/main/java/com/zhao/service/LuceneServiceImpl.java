package com.zhao.service;

import com.zhao.entity.Product;
import com.zhao.util.LuceneUtil;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author aotu
 * @date 2019/1/2 17:03
 */
@Service
public class LuceneServiceImpl implements LuceneService {


    @Override
    public void add(Product product) {
        IndexWriter indexWriter = null;
        try {
            indexWriter = LuceneUtil.getIndexWriter();
            Document document = new Document();
            Random random = new Random();
            product.setId(random.nextInt(1000));
            document.add(new IntField("id", product.getId(), Field.Store.YES));
            document.add(new StringField("name", product.getName(), Field.Store.YES));
            document.add(new DoubleField("price", product.getPrice(), Field.Store.YES));
            document.add(new TextField("description", product.getDescription(), Field.Store.YES));
            document.add(new StringField("imgPath", product.getImgPath(), Field.Store.YES));
            document.add(new StringField("location", product.getLocation(), Field.Store.NO));
            document.add(new StringField("status", product.getStatus(), Field.Store.NO));
            document.add(new StringField("date", product.getDate().toString(), Field.Store.NO));
            indexWriter.addDocument(document);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }

    @Override
    public List<Product> search(String k) {
        IndexSearcher indexSearcher = null;
        List<Product> list = new ArrayList<>();
        try {
            indexSearcher = LuceneUtil.getIndexSearcher();
            TopDocs topDocs = indexSearcher.search(new TermQuery(new Term("description", k)), 100);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (ScoreDoc scoreDoc : scoreDocs) {
                Product product = new Product();
                Document doc = indexSearcher.doc(scoreDoc.doc);
                product.setId(Integer.valueOf(doc.get("id")));
                product.setName(doc.get("name"));
                product.setDescription(doc.get("description"));
                product.setPrice(Double.valueOf(doc.get("price")));
                product.setImgPath(doc.get("imgPath"));
                list.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
