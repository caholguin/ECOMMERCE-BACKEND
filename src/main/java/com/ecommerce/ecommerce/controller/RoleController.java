package com.ecommerce.ecommerce.controller;


import com.ecommerce.ecommerce.dto.RoleDTO;
import com.ecommerce.ecommerce.dto.RoleSummaryDTO;
import com.ecommerce.ecommerce.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Page<RoleDTO>> findAll(Pageable pageable){
        Page<RoleDTO> roles = roleService.findAll(pageable);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
        RoleDTO role = roleService.findById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleDTO> create(@RequestBody RoleDTO roleDto) {
        RoleDTO role = roleService.save(roleDto);
        return new ResponseEntity<>(role,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleSummaryDTO> update(@PathVariable Long id, @RequestBody @Valid RoleDTO roleDTO) {
        RoleSummaryDTO role = roleService.update(id,roleDTO);
        return new ResponseEntity<>(role,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
