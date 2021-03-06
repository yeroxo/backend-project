import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/files")
public class MainServlet extends HttpServlet {
    protected final String DEFAULT_PATH = "/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getPath = req.getParameter("path");
        getPath = getPath == null || getPath.length() == 0 ? DEFAULT_PATH : getPath;

        Path path = Paths.get(getPath);
        FileModel file = new FileModel(path.toFile());
        path.getParent();

        if (file.isDirectory()) {
            req.setAttribute("dateNow", new SimpleDateFormat("MM.dd.yyyy HH:mm:ss").format(new Date()));
            req.setAttribute("path", path);
            req.setAttribute("files", scan(path));
            req.getRequestDispatcher("mypage.jsp").forward(req, resp);
        }

        if (file.isFile()) {
            resp.setHeader("Content-Type", "application/octet-stream");
            resp.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            new FileReader(file.getFile()).transferTo(resp.getWriter());
        }
    }

    protected List<FileModel> scan(Path path) throws IOException {
        return Files.list(path)
                .map(Path::toFile)
                .sorted(this::comparator)
                .map(this::fileFormatter)
                .collect(Collectors.toList());
    }

    protected int comparator(File a, File b) {
        return a.isDirectory() && b.isDirectory() || a.isFile() && b.isFile()
                ? a.compareTo(b)
                : Boolean.compare(a.isFile(), b.isFile());
    }

    protected FileModel fileFormatter(File a){
        return new FileModel(a);
    }
}

