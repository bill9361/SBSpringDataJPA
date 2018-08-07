package cn.bill.sbspringdatajpa;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import cn.bill.sbspringdatajpa.dao.PersonDao;
import cn.bill.sbspringdatajpa.entity.Person;
import cn.bill.sbspringdatajpa.util.TimeUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SbSpringDataJpaApplicationTests {
	
	@Autowired
	private PersonDao personDao;

	@Test
	public void contextLoads() {
	}
	
	/**
	 * 新增或更新人员信息
	 * @throws Exception 
	 */
	@Test
	public void addOrupdateTest() throws Exception
	{
		List<Person> pList = new ArrayList<>();
		for (int i = 0; i < 10; i++)
		{
			Person p = new Person();
			p.setId(UUID.randomUUID().toString());
			//p.setId("c0d61f21-963d-498e-8720-390c6bdbfaa5");
			p.setName("张大"+i);
			p.setPhoneNo("123456789");
			p.setAge(20);
			p.setCreateTime(TimeUtil.getCurrentTime());
			pList.add(p);
		}
		//保存或更新
		personDao.saveAll(pList);
		System.out.println("添加或更新成功");
	}
	/**
	 * 根据Id删除
	 * @throws Exception
	 */
	@Test
	public void deleteTest() throws Exception
	{
		personDao.deleteById("c0d61f21-963d-498e-8720-390c6bdbfaa5");
		System.out.println("删除成功");
	}
	
	/**
	 * 根据name模糊查询人员信息
	 * @throws Exception
	 */
	@Test
	public void findByName() throws Exception
	{
		List<Person> personList = personDao.findByNameLike("%张%");
		System.out.println(personList);
	}

	/**
	 * 根据name模糊查询人员信息
	 * @throws Exception
	 */
	@Test
	public void queryByName() throws Exception
	{
		List<Person> personList = personDao.findByName("%张%");
		System.out.println(personList);
	}
	
	/**
	 * 根据name模糊查询人员信息+分页
	 * @throws Exception
	 */
	@Test
	public void queryByNameLike() throws Exception
	{
		//第1页，取3条
		//Pageable pageable = PageRequest.of(0, 3);
		//第4页，取3条
		Pageable pageable = PageRequest.of(3, 3);
		Page<Person> personPage = personDao.findByNameLike("%张%", pageable);
		for (Person person : personPage.getContent())
		{
			System.out.println(person);
		}
	}
}
