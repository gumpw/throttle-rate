package com.weihui.common.throttle.strategy.bucket;

import java.util.concurrent.TimeUnit;


/**
 * <p>
 * 固定token限流策略.
 * </p>
 * 每次对{@link FixedTokenBucketStrategy#isThrottled()}方法的调用都会减少token值
 *
 * 该策略采用{@see <a href="http://en.wikipedia.org/wiki/Token_bucket">Token Bucket</a>}
 * @author Magic Joey
 * @version Throttle.java 1.0 @2015/12/28 20:34 $
 */
public class FixedTokenBucketStrategy extends TokenBucketStrategy {

	public FixedTokenBucketStrategy(long bucketTokenCapacity, long refillInterval, TimeUnit refillIntervalTimeUnit) {
		super(bucketTokenCapacity, refillInterval, refillIntervalTimeUnit);
	}

	@Override
    protected void updateTokens() {
        // 如果当前时间超过下一次refill时间，则执行refill
        long currentTime = System.currentTimeMillis();
        if(currentTime < nextRefillTime) return;

        tokens = bucketTokenCapacity;
        nextRefillTime = currentTime + refillInterval;
    }
}
