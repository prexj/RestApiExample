package com.monarch.UM.table.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monarch.UM.table.model.TableConfiguration;
import com.monarch.UM.table.model.TableProperties;
import com.monarch.UM.table.service.TableConfigurationService;
import com.monarch.UM.table.service.TablePropsService;

@RequestMapping("/tableProps")
@RestController
public class TablePropsAndTableConfigRestController {
	@Autowired
	private TablePropsService tablePropsService;
	
	@Autowired
	private TableConfigurationService tableConfigService;

	@PostMapping
	public ResponseEntity<HashMap<String, Object>> saveTablePops(@RequestBody TableProperties tableProps) {
		System.out.println("in save table properties");
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			TableProperties tPops = tablePropsService.findByName(tableProps.getName());
			if (tPops == null) {
				TableProperties tPopsR =tablePropsService.save(tableProps);
				map.put("tableProps", tPopsR);
				map.put("msg", "Table Properties is saved");
			} else {
				map.put("msg", "Table Properties  Already Exist! Try Again");
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<HashMap<String, Object>> UpdateTableProps(@PathVariable("id") Long id, @RequestBody TableProperties tableProps) {
		TableProperties tablePropFind=tablePropsService.findById(id);
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			if (tablePropFind != null) {
				//tableProps.setId(tablePropFind.getId());
				TableProperties afterUpdateTableProps=tablePropsService.save(tableProps);
				map.put("tableProps", afterUpdateTableProps);
				map.put("msg", "Table Properties is Updated");
			} else {
				map.put("msg", "Sorry, This Table Properties does not Exist in our application ! Try Again with Proper userId");
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@DeleteMapping("/{id}")
	public boolean deletetablePops(@PathVariable("id") Long id) {
		return tablePropsService.delete(id);
	}
	
	
	@PostMapping("/{id}/tableConfig")
	public ResponseEntity<HashMap<String, Object>> saveTableConfig(@PathVariable("id") Long id,
			@RequestBody TableConfiguration tableConfig) {
		System.out.println("in save table configuration");
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			TableProperties tableProps = tablePropsService.findById(id);
			if (tableProps == null) {
				map.put("msg", "Table Properties does not exist");
			} else {
				tableConfig.setTableProps(tableProps);
				tableConfigService.save(tableConfig);
				map.put("tabelConfig", tableConfig);
				map.put("msg", "Table Configuration is saved.");

				return new ResponseEntity<>(map, HttpStatus.OK);
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@PutMapping("/{id}/tableConfig/{tableConfigId}")
	public ResponseEntity<HashMap<String, Object>> saveTableConfiguration(@PathVariable("id") Long id,
			@PathVariable("tableConfigId") Long tableConfigId, @RequestBody TableConfiguration tableConfig) {
		System.out.println("in Update table configuration");
		HashMap<String, Object> map = new HashMap<String, Object>();
		TableProperties tableProps = tablePropsService.findById(id);
		tableConfig.setId(tableConfigId);
		try {
			if (tableProps == null) {
				map.put("msg", "Table Properties does not exist");
			} else {
				//userProfile.setUser(user);
				//tableConfigService.findById(tableConfigId);
				tableConfig.setTableProps(tableProps);
				tableConfigService.save(tableConfig);
				map.put("tabelConfig", tableConfig);
				map.put("msg", "Table Configuration is Updated.");

				return new ResponseEntity<>(map, HttpStatus.OK);
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	@DeleteMapping("/{id}/tableConfig")
	public boolean deleteTableConfiguration(@PathVariable("id") Long id) {
		return tableConfigService.delete(id);
	}

}
