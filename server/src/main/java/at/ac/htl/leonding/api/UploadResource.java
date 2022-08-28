package at.ac.htl.leonding.api;

import at.ac.htl.leonding.workloads.song.Song;
import at.ac.htl.leonding.workloads.song.SongRepo;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


@Path("/uploadFile")
public class UploadResource {

    @Inject
    SongRepo repo;

    private final String UPLOADED_FILE_PATH = "/opt/";

    //private final String UPLOADED_FILE_PATH = "C:\Schule\4BHITM\sew\musictech\files";

    //private final String UPLOADED_FILE_PATH = "/home/marcel/musictech/files/";
    private String postURL = "";

    @POST
    @Path("/upload")
    @Consumes("multipart/form-data")
    @Transactional
    public Response uploadFile(MultipartFormDataInput input) {

        String fileName = "";
        String artist = "";
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("uploadedFile");

        for (InputPart inputPart : inputParts) {

            try {
                MultivaluedMap<String, String> header = inputPart.getHeaders();
                fileName = getFileName(header);
                artist = getArtist(header);
                System.out.println(artist);
                InputStream inputStream = inputPart.getBody(InputStream.class, null);
                System.out.println(fileName);

                postURL = "/api/uploadFile/download/" + fileName;


                //postURL = "http://localhost:8080/uploadFile/download/" + fileName;
                System.out.println(postURL);
                Song song = new Song(fileName, postURL);
                repo.addSong(song);
                //repo.persist(song);
                //songService.addSong(fileName,artist,postURL);
                byte[] bytes = IOUtils.toByteArray(inputStream);
                fileName = UPLOADED_FILE_PATH + fileName;
                System.out.println(fileName + ": 62");

                writeFile(bytes, fileName);
                System.out.println("Done");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return Response.status(200)
                .entity("{\"message\":\"uploadFile is called, Uploaded file name : " + fileName + "\"}").build();
    }

    @GET
    @Path("/download/{fileName}")
    @Produces({"audio/mpeg"})
    public Response downloadFile(@PathParam("fileName") String fileName) {

        File audioFile = new File(UPLOADED_FILE_PATH + fileName);
        if (!audioFile.exists()) {
            throw new RuntimeException("File not found: " + UPLOADED_FILE_PATH + fileName);
        }
        Response.ResponseBuilder res = Response.ok((Object) audioFile);
        res.header("Content-Disposition", "inline;filename=" + fileName);
        return res.build();
    }

    private String getFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }

    private String getArtist(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("artist"))) {
                String[] name = filename.split("=");
                String finalArtist = name[1].trim().replaceAll("\"", "");
                return finalArtist;
            }
        }
        return "unknown";
    }


    private void writeFile(byte[] content, String filename) throws IOException {
        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
            System.out.println("succes");
        }
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(content);
        fop.flush();
        fop.close();

    }


}
