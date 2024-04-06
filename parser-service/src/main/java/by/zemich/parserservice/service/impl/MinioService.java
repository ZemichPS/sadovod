package by.zemich.parserservice.service.impl;

import by.zemich.parserservice.service.api.IContentHandler;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinioService implements IContentHandler {

    private final MinioClient client;

    public MinioService(MinioClient client) {
        this.client = client;
    }

    @Override
    public boolean save(String to, FileInputStream fileInputStream, String filename) {

        PutObjectArgs objectArgs = null;
        try {
            objectArgs = PutObjectArgs.builder()
                    .bucket(to)
                    .object(filename)
                    .stream(fileInputStream, fileInputStream.getChannel().size(), -1)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            client.putObject(objectArgs);
        } catch (ErrorResponseException | InternalException | InsufficientDataException | InvalidKeyException |
                 IOException | ServerException | InvalidResponseException | NoSuchAlgorithmException |
                 XmlParserException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public InputStream get(String from, String filename) {
        GetObjectArgs objectArgs = GetObjectArgs
                .builder()
                .bucket(from)
                .object(filename)
                .build();
        InputStream stream = null;
        try {
            stream = client.getObject(objectArgs);
        } catch (ErrorResponseException | InsufficientDataException e) {
            throw new RuntimeException(e);
        } catch (InternalException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (InvalidResponseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        } catch (XmlParserException e) {
            throw new RuntimeException(e);
        }

        return stream;
    }
}
