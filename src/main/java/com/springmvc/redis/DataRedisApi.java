package com.springmvc.redis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;

/**
 * redis抽象服务类
 */
public abstract class DataRedisApi {
	
	@Resource(name="shardedJedisPool")
	protected ShardedJedisPool shardedJedisPool;

	protected ShardedJedis getJedis() {
		return this.shardedJedisPool.getResource();
	}

	
	/************************* key操作 ***********************************/
	protected Set<String> getKeys(String pattern){
		ShardedJedis jedis = getJedis();
		Set<String> set = new HashSet<String>();
		try {
			Collection<Jedis> shards = jedis.getAllShards();
			for (Jedis shard : shards) {
				set.addAll(shard.keys(pattern));
			}
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return set;
	}
	
	protected boolean exists(String key) {
		ShardedJedis jedis = getJedis();
		boolean flag = false;
		try {
			flag = jedis.exists(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return flag;
	}
	protected void set(String key, String value) {
		ShardedJedis jedis = getJedis();
		try {
			jedis.set(key, value);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}

	protected String get(String key) {
		ShardedJedis jedis = getJedis();
		String r = "";
		try {
			r = jedis.get(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			e.printStackTrace();
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return r;
	}

	protected void expire(String key, int seconds) {
		ShardedJedis jedis = getJedis();
		try {
			jedis.expire(key, seconds);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}

	protected void del(String key) {
		ShardedJedis jedis = getJedis();
		try {
			jedis.del(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}

	/*********************** hash操作 *******************************/
	protected void hset(String key, String field, String value) {
		ShardedJedis jedis = getJedis();
		try {
			jedis.hset(key, field, value);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}
	
	protected void hdel(String key, String field) {
		ShardedJedis jedis = getJedis();
		try {
			jedis.hdel(key, field);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}

	protected void hmset(String key, Map<String, String> hash) {
		ShardedJedis jedis = getJedis();
		try {
			jedis.hmset(key, hash);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}

	protected Boolean hexists(String key, String field) {
		ShardedJedis jedis = getJedis();
		Boolean result = false;
		try {
			result = jedis.hexists(key, field);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return result;
	}

	protected String hget(String key, String field) {
		ShardedJedis jedis = getJedis();
		String result = "";
		try {
			result = jedis.hget(key, field);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return result;
	}

	protected Map<String, String> hgetAll(String key) {
		ShardedJedis jedis = getJedis();
		Map<String, String> result = new HashMap<String, String>();
		try {
			result = jedis.hgetAll(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return result;
	}

	protected Long hincrBy(String key, String field, long value) {
		ShardedJedis jedis = getJedis();
		Long r = 0L;
		try {
			r = jedis.hincrBy(key, field, value);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return r;
	}

	protected List<String> sort(String key, SortingParams params) {
		ShardedJedis jedis = getJedis();
		List<String> list = new ArrayList<String>();
		try{
			list = jedis.sort(key, params);
		}catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return list;
	}

	/**************************** set操作 ******************************************/
	protected Long sadd(String key, String... members) {
		ShardedJedis jedis = getJedis();
		Long r = 0L;
		try {
			r = jedis.sadd(key, members);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return r;
	}

	protected Long srem(String key, String members) {
		ShardedJedis jedis = getJedis();
		Long r = 0L;
		try {
			r = jedis.srem(key, members);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return r;
	}

	protected Long scard(String key) {
		ShardedJedis jedis = getJedis();
		Long result = 0L;
		try {
			result = jedis.scard(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return result;
	}

	protected Set<String> smembers(String key) {
		ShardedJedis jedis = getJedis();
		Set<String> result = new HashSet<String>();
		try {
			result = jedis.smembers(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return result;
	}

	protected boolean sismember(String key, String member) {
		ShardedJedis jedis = getJedis();
		boolean result = false;
		try {
			result = jedis.sismember(key, member);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return result;
	}

	/********************************* zset操作 ******************************************/
	protected Long zadd(String key, double score, String member) {
		ShardedJedis jedis = getJedis();
		Long r = 0L;
		try {
			r = jedis.zadd(key, score, member);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return r;
	}
	//特殊处理
	protected Set<String> zrevrange(String key, long start, long size) {
		ShardedJedis jedis = getJedis();
		Set<String> result = new HashSet<String>();
		try {
			long end = 0;
			if(0 >= size){
				end = size;
			}else{
				end = start + size - 1;
			}
			result = jedis.zrevrange(key, start, end);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return result;
	}
	//特殊处理
	protected Set<String> zrange(String key, long start, long size) {
		ShardedJedis jedis = getJedis();
		Set<String> result = new HashSet<String>();
		try {
			long end = 0;
			if(0 >= size){
				end = size;
			}else{
				end = start + size - 1;
			}
			result = jedis.zrange(key, start, end);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return result;
	}
	protected Set<String> zrevrangeByScore(String key, double max, double min) {
		ShardedJedis jedis = getJedis();
		Set<String> result = new HashSet<String>();
		try {
			result = jedis.zrevrangeByScore(key, max, min);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return result;
	}
	protected Set<String> zrevrangeByScore(String key, double max, double min,
			int offset, int count) {
		ShardedJedis jedis = getJedis();
		Set<String> result = new HashSet<String>();
		try {
			result = jedis.zrevrangeByScore(key, max, min, offset, count);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return result;
	}
	// 获取成员排名 
	protected Long zrevrank(String key,String member) {
		ShardedJedis jedis = getJedis();
		Long result = 0L;
		try {
			result = jedis.zrevrank(key, member);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return result;
	}
	protected long zcard(String key) {
		ShardedJedis jedis = getJedis();
		long result = 0L;
		try {
			result = jedis.zcard(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return result;
	}

	protected void zrem(String key, String... members) {
		ShardedJedis jedis = getJedis();
		try {
			jedis.zrem(key, members);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}

	protected Double zscore(String key, String member) {
		ShardedJedis jedis = getJedis();
		Double r = 0.0;
		try {
			r = jedis.zscore(key, member) == null ? 0.0 : jedis.zscore(key,
					member);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
		return r;
	}

	protected void zincrby(String key, double score, String member) {
		ShardedJedis jedis = getJedis();
		try {
			jedis.zincrby(key, score, member);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
		} finally {
			shardedJedisPool.returnResource(jedis);
		}
	}
}
