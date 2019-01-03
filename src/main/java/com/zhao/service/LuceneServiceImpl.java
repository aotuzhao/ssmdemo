package com.zhao.service;

import com.zhao.entity.Product;
import com.zhao.util.LuceneUtil;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

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
            document.add(new StringField("location", product.getLocation(), Field.Store.YES));
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
        String[] strs = {"name", "description", "location"};
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_44, strs, new IKAnalyzer());
        List<Product> list = new ArrayList<>();
        try {
            Query query = multiFieldQueryParser.parse(k);
            indexSearcher = LuceneUtil.getIndexSearcher();
            TopDocs topDocs = indexSearcher.search(query, 100);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
            Scorer scorer = new QueryScorer(query);
            Highlighter highlighter = new Highlighter(formatter, scorer);
            for (ScoreDoc scoreDoc : scoreDocs) {
                Product product = new Product();
                Document doc = indexSearcher.doc(scoreDoc.doc);
                String bestFragment = highlighter.getBestFragment(new IKAnalyzer(), "name", doc.get("name"));
                if (bestFragment == null) {
                    bestFragment = doc.get("name");
                }
                String description = highlighter.getBestFragment(new IKAnalyzer(), "description", doc.get("description"));
                if (description == null) {
                    description = doc.get("description");
                }
                product.setId(Integer.valueOf(doc.get("id")));
                product.setName(bestFragment);
                product.setDescription(description);
                product.setPrice(Double.valueOf(doc.get("price")));
                product.setImgPath(doc.get("imgPath"));
                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
