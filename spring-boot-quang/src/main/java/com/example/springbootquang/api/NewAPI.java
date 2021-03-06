package com.example.springbootquang.api;

import com.example.springbootquang.api.output.NewOutput;
import com.example.springbootquang.dto.NewDTO;
import com.example.springbootquang.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//Annotation @ResponseBody được thêm vào trước các method của các controller để chỉ dẫn rằng method này sẽ trả về text thay vì trả về view.
///Annotation @RestController được dùng trước các class, các method trong class này sẽ trả về text thay vì trả về view.
@RestController
@RequestMapping("/api/v1")
public class NewAPI {
    @Autowired
    private INewService newService;

    @GetMapping(value = "/new/{id}", produces = "application/json")
    public ResponseEntity<?> getNewById( @PathVariable(name = "id") Long id){
        //Kiểm tra tồn tại ko
        NewDTO newDTO = newService.findById(id);
        if (newDTO == null){
            Map<String, String> error = new HashMap<>();
            error.put("statusCode", "404");
            error.put("message", "Không tồn tại đối tượng");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
//        if(Objects.isNull(newDTO)){
//            return new ResponseEntity<>(newDTO,
//                    HttpStatus.NO_CONTENT);
//        }
        return  new ResponseEntity<>(newDTO, HttpStatus.OK);
    }

    //Làm API tính phân trang: page là trang hiện tại, limit là số trang tối đa trên 1 page
    //dựa vào trang hiện tại tính ra totalPage và offset
    @GetMapping("/new")
    public ResponseEntity<?> showNew(@RequestParam("page") int page,
                                     @RequestParam("limit") int limit){
        NewOutput result = new NewOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);

        result.setListResult(newService.findAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (newService.totalItem()) / limit));
        if(result == null){
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<?>  createNew(@RequestBody NewDTO modelDto) {
        NewDTO newD = newService.save(modelDto);

        return new ResponseEntity<>(newD, HttpStatus.OK);
    }

    @PutMapping(value = "/new/{id}")
    public ResponseEntity<?> updateNew(@RequestBody NewDTO newDto, @PathVariable("id") long id) {

        NewDTO currentnewDto = newService.findById(id);
        if (currentnewDto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        newDto.setId(id);
        currentnewDto.setId(newDto.getId());
        currentnewDto.setContent(newDto.getContent());
        currentnewDto.setThumbnail(newDto.getThumbnail());
        currentnewDto.setTitle(newDto.getTitle());
        currentnewDto.setShortDescription(newDto.getShortDescription());
        currentnewDto.setCategoryCode(newDto.getCategoryCode());
        currentnewDto.setListResult(newDto.getListResult());

        NewDTO q = newService.save(currentnewDto);
        System.out.println(q.getId());
        return new ResponseEntity<>(q, HttpStatus.OK);
    }

    @DeleteMapping(value = "/new")
    public ResponseEntity<?> deleteNew(@RequestBody long[] ids) {
        try {
            newService.delete(ids);
        }catch (Exception ex){
            System.out.println("K xóa dc");
            Map<String, String> error = new HashMap<>();
            error.put("statusCode", "404");
            error.put("message", "NOT_FOUND");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        Map<String, String> error = new HashMap<>();
        System.out.println("chay vao?");
        error.put("statusCode", "204");
        error.put("message", "NO_CONTENT");
        System.out.println("chay vao?");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(error);
    }
    //Xóa 1
    @DeleteMapping(value = "/new/{id}")
    public ResponseEntity<?> deleteOneNew(@PathVariable(name = "id") Long id){
        NewDTO newDTO = newService.findById(id);//kiểm tra

        if(newDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        newService.deleteOne(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
