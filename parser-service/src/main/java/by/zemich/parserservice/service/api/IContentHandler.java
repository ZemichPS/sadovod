package by.zemich.parserservice.service.api;

import java.io.FileInputStream;
import java.io.InputStream;

public interface IContentHandler {
    boolean save(String to, FileInputStream fileInputStream, String filename);
    InputStream get(String from, String filename);
}
