package gr.aueb.cf.jaxpro.rest;

import gr.aueb.cf.jaxpro.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.jaxpro.model.Teacher;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Path("/hello")
public class HelloRestController {

    @GET
    @Path("/say-hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
        return "Hello Coding Factory!!!";
    }

    @GET
    @Path("/get-hello-res")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHelloRes() {
//        return Response.ok("Hello Coding Factory AGAIN!!!").build();
        return Response.status(Response.Status.OK)
                .entity("Hello World!!")
                .build();
    }

    @GET
    @Path("/get-teacher")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeacher() {
        Teacher teacher = new Teacher(1L, "SSN123", "Athanasios", "Androutsos");
        TeacherReadOnlyDTO readOnlyDTO = teacher.toReadOnlyDTO();
        return Response.status(Response.Status.OK).entity(readOnlyDTO).build();
    }

    @GET
    @Path("/teachers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeachers(@QueryParam("lastname") String lastname) {
        List<Teacher> teacherList = List.of(
                new Teacher(1L, "SSN123", "Athanasios", "Androutsos"),
                new Teacher(2L, "SSN234", "Nick", "Androutsos")
                );
        if (teacherList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Teacher not Found").build();
        }

        List<TeacherReadOnlyDTO> teacherReadOnlyDTOS = new ArrayList<>();
        for (Teacher teacher : teacherList) {
            teacherReadOnlyDTOS.add(teacher.toReadOnlyDTO());
        }

        return Response.status(Response.Status.OK).entity(teacherReadOnlyDTOS).build();
    }
}
