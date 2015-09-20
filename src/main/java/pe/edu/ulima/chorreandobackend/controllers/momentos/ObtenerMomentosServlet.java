
package pe.edu.ulima.chorreandobackend.controllers.momentos;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.edu.ulima.chorreandobackend.dao.Momento;
import pe.edu.ulima.chorreandobackend.dto.momentos.ObtenerMomentosResponse;
import pe.edu.ulima.chorreandobackend.models.GestorMomentos;

public class ObtenerMomentosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        ObtenerMomentosResponse obtenerMomentosResponse;
        
        if (request.getPathInfo() == null){
            obtenerMomentosResponse = new ObtenerMomentosResponse(
                        "EXCEPTION", 
                        "(4XX): No ha enviado un idUsuario", 
                        null
                );
        }else{
            String idUsuario = request.getPathInfo().substring(1);

            System.out.println("IDUSUARIO:" + idUsuario);

            List<Momento> momentos = 
                    GestorMomentos.getInstance().obtenerMomentos(
                            Long.parseLong(idUsuario));

            obtenerMomentosResponse = 
                    new ObtenerMomentosResponse(
                            "OK", 
                            "", 
                            momentos
                    );
        }
        
        String json = new Gson().toJson(obtenerMomentosResponse);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(json);
    } 

    
}