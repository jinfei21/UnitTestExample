package com.finix.test.util;

import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest  {

    @Mock
    private RedisService redisTemplate;

    @InjectMocks
    RedisUtils redisUtil;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: set(final K key, final V value, final long expiredTimeSeconds)
     */
    @Test
    public void testSet() throws Exception {
        ValueOperations mock_BoundValueOperations = (ValueOperations) mock(ValueOperations.class);
        Mockito.when(redisTemplate.boundValueOps(BDDMockito.anyString())).thenReturn(mock_BoundValueOperations);
        Mockito.doNothing().when(mock_BoundValueOperations).set(BDDMockito.anyString());
        redisUtil.set("abc", "abc", -10);
        Mockito.verify(mock_BoundValueOperations, Mockito.times(1))
        .set(
                BDDMockito.anyString()
        );
    }

    /**
     * Method: get(final K key)
     */
    @Test
    public void testGet() throws Exception {
        ValueOperations mock_BoundValueOperations = (ValueOperations) mock(ValueOperations.class);

        Mockito.when(redisTemplate.boundValueOps(BDDMockito.anyString())).thenReturn(mock_BoundValueOperations);
        Mockito.when(mock_BoundValueOperations.get()).thenReturn("1235");
        String cache = (String) redisUtil.get("abc");
        Mockito.verify(mock_BoundValueOperations, Mockito.times(1)).get();
        Assert.assertEquals(cache, "1235");

    }


} 
