package com.satori.rtm.model;

import com.google.common.base.Preconditions;
import com.satori.rtm.ReqReadMode;

/**
 * Represents the body of a Protocol Data Unit (<strong>PDU</strong>) for a write request.
 * <p>
 * The PDU has the following basic structure:
 * <pre>{@literal
 * {
 *     "action": "rtm/write",
 *     "body": {
 *         "channel": ChannelName,
 *         "message": Message,
 *         "position": Position OPTIONAL
 *     },
 *     "id": RequestId OPTIONAL
 * }}
 * </pre>
 */
public class WriteRequest<T> {
  private String channel;
  private T message;
  private String position;
  private Long ttl;
  //TODO: Use separate generic type for the TTL message, in case message types are different.
  private T ttl_message;

  private String read;

  public WriteRequest() { }

  public WriteRequest(String channel, T message) {
    this(channel, message, null, null);
  }

  public WriteRequest(String channel, T message, String position) {
    this.channel = channel;
    this.message = message;
    this.position = position;
  }

  public WriteRequest(String channel, T message, String position, ReqReadMode returnMode) {
    this.channel = channel;
    this.message = message;
    this.position = position;
    this.read =  returnMode == null ? null : returnMode.toString();
  }

  public WriteRequest(String channel, T message, String position, final long ttl, final T ttl_message) {
    Preconditions.checkArgument(ttl > 0, String.format("ttl must be non negative: %s", ttl));
    this.channel = channel;
    this.message = message;
    this.position = position;
    this.ttl = ttl;
    this.ttl_message = ttl_message;
  }

  public WriteRequest(String channel, T message, String position, final long ttl, final T ttl_message, final ReqReadMode returnMode) {
    Preconditions.checkArgument(ttl > 0, String.format("ttl must be non negative: %s", ttl));
    this.channel = channel;
    this.message = message;
    this.position = position;
    this.ttl = ttl;
    this.ttl_message = ttl_message;
    this.read =  returnMode == null ? null : returnMode.toString();
  }

  public String getChannel() {
    return channel;
  }

  public T getMessage() {
    return message;
  }

  public String getPosition() {
    return position;
  }

  public Long getTtl() {
    return ttl;
  }

  public T getTtlMessage() {
    return ttl_message;
  }

  public WriteRequest<T> withTtl(final long newTtl, final T newTtlMessage) {
    return new WriteRequest<T>(channel, message, position, newTtl, newTtlMessage);
  }

  public WriteRequest<T> withRequestReturn(final long newTtl, final ReqReadMode returnMode) {
    return new WriteRequest<T>(channel, message, position, returnMode);
  }

  @Override
  public String toString() {
    return "WriteRequest{" +
        "channel='" + channel + '\'' +
        ", message=" + message +
        ", position='" + position + '\'' +
        ", ttl=" + ttl +
        ", ttlMessage=" + ttl_message +
        '}';
  }
}
