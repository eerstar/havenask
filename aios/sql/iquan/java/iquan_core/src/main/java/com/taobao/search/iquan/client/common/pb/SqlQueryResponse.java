// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: iquan.proto

package com.taobao.search.iquan.client.common.pb;

/**
 * Protobuf type {@code iquan.pb.SqlQueryResponse}
 */
public  final class SqlQueryResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:iquan.pb.SqlQueryResponse)
    SqlQueryResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SqlQueryResponse.newBuilder() to construct.
  private SqlQueryResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SqlQueryResponse() {
    errorMessage_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new SqlQueryResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SqlQueryResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            errorCode_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            errorMessage_ = s;
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              debugInfos_ = com.google.protobuf.MapField.newMapField(
                  DebugInfosDefaultEntryHolder.defaultEntry);
              mutable_bitField0_ |= 0x00000001;
            }
            com.google.protobuf.MapEntry<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue>
            debugInfos__ = input.readMessage(
                DebugInfosDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
            debugInfos_.getMutableMap().put(
                debugInfos__.getKey(), debugInfos__.getValue());
            break;
          }
          case 34: {
            com.taobao.search.iquan.client.common.pb.SqlPlan.Builder subBuilder = null;
            if (result_ != null) {
              subBuilder = result_.toBuilder();
            }
            result_ = input.readMessage(com.taobao.search.iquan.client.common.pb.SqlPlan.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(result_);
              result_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.taobao.search.iquan.client.common.pb.Iquan.internal_static_iquan_pb_SqlQueryResponse_descriptor;
  }

  @SuppressWarnings({"rawtypes"})
  @java.lang.Override
  protected com.google.protobuf.MapField internalGetMapField(
      int number) {
    switch (number) {
      case 3:
        return internalGetDebugInfos();
      default:
        throw new RuntimeException(
            "Invalid map field number: " + number);
    }
  }
  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.taobao.search.iquan.client.common.pb.Iquan.internal_static_iquan_pb_SqlQueryResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.taobao.search.iquan.client.common.pb.SqlQueryResponse.class, com.taobao.search.iquan.client.common.pb.SqlQueryResponse.Builder.class);
  }

  public static final int ERROR_CODE_FIELD_NUMBER = 1;
  private int errorCode_;
  /**
   * <code>int32 error_code = 1;</code>
   */
  public int getErrorCode() {
    return errorCode_;
  }

  public static final int ERROR_MESSAGE_FIELD_NUMBER = 2;
  private volatile java.lang.Object errorMessage_;
  /**
   * <code>string error_message = 2;</code>
   */
  public java.lang.String getErrorMessage() {
    java.lang.Object ref = errorMessage_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      errorMessage_ = s;
      return s;
    }
  }
  /**
   * <code>string error_message = 2;</code>
   */
  public com.google.protobuf.ByteString
      getErrorMessageBytes() {
    java.lang.Object ref = errorMessage_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      errorMessage_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DEBUG_INFOS_FIELD_NUMBER = 3;
  private static final class DebugInfosDefaultEntryHolder {
    static final com.google.protobuf.MapEntry<
        java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue> defaultEntry =
            com.google.protobuf.MapEntry
            .<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue>newDefaultInstance(
                com.taobao.search.iquan.client.common.pb.Iquan.internal_static_iquan_pb_SqlQueryResponse_DebugInfosEntry_descriptor, 
                com.google.protobuf.WireFormat.FieldType.STRING,
                "",
                com.google.protobuf.WireFormat.FieldType.MESSAGE,
                com.taobao.search.iquan.client.common.pb.AnyValue.getDefaultInstance());
  }
  private com.google.protobuf.MapField<
      java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue> debugInfos_;
  private com.google.protobuf.MapField<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue>
  internalGetDebugInfos() {
    if (debugInfos_ == null) {
      return com.google.protobuf.MapField.emptyMapField(
          DebugInfosDefaultEntryHolder.defaultEntry);
    }
    return debugInfos_;
  }

  public int getDebugInfosCount() {
    return internalGetDebugInfos().getMap().size();
  }
  /**
   * <code>map&lt;string, .iquan.pb.AnyValue&gt; debug_infos = 3;</code>
   */

  public boolean containsDebugInfos(
      java.lang.String key) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    return internalGetDebugInfos().getMap().containsKey(key);
  }
  /**
   * Use {@link #getDebugInfosMap()} instead.
   */
  @java.lang.Deprecated
  public java.util.Map<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue> getDebugInfos() {
    return getDebugInfosMap();
  }
  /**
   * <code>map&lt;string, .iquan.pb.AnyValue&gt; debug_infos = 3;</code>
   */

  public java.util.Map<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue> getDebugInfosMap() {
    return internalGetDebugInfos().getMap();
  }
  /**
   * <code>map&lt;string, .iquan.pb.AnyValue&gt; debug_infos = 3;</code>
   */

  public com.taobao.search.iquan.client.common.pb.AnyValue getDebugInfosOrDefault(
      java.lang.String key,
      com.taobao.search.iquan.client.common.pb.AnyValue defaultValue) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    java.util.Map<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue> map =
        internalGetDebugInfos().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  /**
   * <code>map&lt;string, .iquan.pb.AnyValue&gt; debug_infos = 3;</code>
   */

  public com.taobao.search.iquan.client.common.pb.AnyValue getDebugInfosOrThrow(
      java.lang.String key) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    java.util.Map<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue> map =
        internalGetDebugInfos().getMap();
    if (!map.containsKey(key)) {
      throw new java.lang.IllegalArgumentException();
    }
    return map.get(key);
  }

  public static final int RESULT_FIELD_NUMBER = 4;
  private com.taobao.search.iquan.client.common.pb.SqlPlan result_;
  /**
   * <code>.iquan.pb.SqlPlan result = 4;</code>
   */
  public boolean hasResult() {
    return result_ != null;
  }
  /**
   * <code>.iquan.pb.SqlPlan result = 4;</code>
   */
  public com.taobao.search.iquan.client.common.pb.SqlPlan getResult() {
    return result_ == null ? com.taobao.search.iquan.client.common.pb.SqlPlan.getDefaultInstance() : result_;
  }
  /**
   * <code>.iquan.pb.SqlPlan result = 4;</code>
   */
  public com.taobao.search.iquan.client.common.pb.SqlPlanOrBuilder getResultOrBuilder() {
    return getResult();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (errorCode_ != 0) {
      output.writeInt32(1, errorCode_);
    }
    if (!getErrorMessageBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, errorMessage_);
    }
    com.google.protobuf.GeneratedMessageV3
      .serializeStringMapTo(
        output,
        internalGetDebugInfos(),
        DebugInfosDefaultEntryHolder.defaultEntry,
        3);
    if (result_ != null) {
      output.writeMessage(4, getResult());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (errorCode_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, errorCode_);
    }
    if (!getErrorMessageBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, errorMessage_);
    }
    for (java.util.Map.Entry<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue> entry
         : internalGetDebugInfos().getMap().entrySet()) {
      com.google.protobuf.MapEntry<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue>
      debugInfos__ = DebugInfosDefaultEntryHolder.defaultEntry.newBuilderForType()
          .setKey(entry.getKey())
          .setValue(entry.getValue())
          .build();
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(3, debugInfos__);
    }
    if (result_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getResult());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.taobao.search.iquan.client.common.pb.SqlQueryResponse)) {
      return super.equals(obj);
    }
    com.taobao.search.iquan.client.common.pb.SqlQueryResponse other = (com.taobao.search.iquan.client.common.pb.SqlQueryResponse) obj;

    if (getErrorCode()
        != other.getErrorCode()) return false;
    if (!getErrorMessage()
        .equals(other.getErrorMessage())) return false;
    if (!internalGetDebugInfos().equals(
        other.internalGetDebugInfos())) return false;
    if (hasResult() != other.hasResult()) return false;
    if (hasResult()) {
      if (!getResult()
          .equals(other.getResult())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ERROR_CODE_FIELD_NUMBER;
    hash = (53 * hash) + getErrorCode();
    hash = (37 * hash) + ERROR_MESSAGE_FIELD_NUMBER;
    hash = (53 * hash) + getErrorMessage().hashCode();
    if (!internalGetDebugInfos().getMap().isEmpty()) {
      hash = (37 * hash) + DEBUG_INFOS_FIELD_NUMBER;
      hash = (53 * hash) + internalGetDebugInfos().hashCode();
    }
    if (hasResult()) {
      hash = (37 * hash) + RESULT_FIELD_NUMBER;
      hash = (53 * hash) + getResult().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.taobao.search.iquan.client.common.pb.SqlQueryResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.taobao.search.iquan.client.common.pb.SqlQueryResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.taobao.search.iquan.client.common.pb.SqlQueryResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.taobao.search.iquan.client.common.pb.SqlQueryResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.taobao.search.iquan.client.common.pb.SqlQueryResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.taobao.search.iquan.client.common.pb.SqlQueryResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.taobao.search.iquan.client.common.pb.SqlQueryResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.taobao.search.iquan.client.common.pb.SqlQueryResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.taobao.search.iquan.client.common.pb.SqlQueryResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.taobao.search.iquan.client.common.pb.SqlQueryResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.taobao.search.iquan.client.common.pb.SqlQueryResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.taobao.search.iquan.client.common.pb.SqlQueryResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.taobao.search.iquan.client.common.pb.SqlQueryResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code iquan.pb.SqlQueryResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:iquan.pb.SqlQueryResponse)
      com.taobao.search.iquan.client.common.pb.SqlQueryResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.taobao.search.iquan.client.common.pb.Iquan.internal_static_iquan_pb_SqlQueryResponse_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 3:
          return internalGetDebugInfos();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMutableMapField(
        int number) {
      switch (number) {
        case 3:
          return internalGetMutableDebugInfos();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.taobao.search.iquan.client.common.pb.Iquan.internal_static_iquan_pb_SqlQueryResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.taobao.search.iquan.client.common.pb.SqlQueryResponse.class, com.taobao.search.iquan.client.common.pb.SqlQueryResponse.Builder.class);
    }

    // Construct using com.taobao.search.iquan.client.common.pb.SqlQueryResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      errorCode_ = 0;

      errorMessage_ = "";

      internalGetMutableDebugInfos().clear();
      if (resultBuilder_ == null) {
        result_ = null;
      } else {
        result_ = null;
        resultBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.taobao.search.iquan.client.common.pb.Iquan.internal_static_iquan_pb_SqlQueryResponse_descriptor;
    }

    @java.lang.Override
    public com.taobao.search.iquan.client.common.pb.SqlQueryResponse getDefaultInstanceForType() {
      return com.taobao.search.iquan.client.common.pb.SqlQueryResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.taobao.search.iquan.client.common.pb.SqlQueryResponse build() {
      com.taobao.search.iquan.client.common.pb.SqlQueryResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.taobao.search.iquan.client.common.pb.SqlQueryResponse buildPartial() {
      com.taobao.search.iquan.client.common.pb.SqlQueryResponse result = new com.taobao.search.iquan.client.common.pb.SqlQueryResponse(this);
      int from_bitField0_ = bitField0_;
      result.errorCode_ = errorCode_;
      result.errorMessage_ = errorMessage_;
      result.debugInfos_ = internalGetDebugInfos();
      result.debugInfos_.makeImmutable();
      if (resultBuilder_ == null) {
        result.result_ = result_;
      } else {
        result.result_ = resultBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.taobao.search.iquan.client.common.pb.SqlQueryResponse) {
        return mergeFrom((com.taobao.search.iquan.client.common.pb.SqlQueryResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.taobao.search.iquan.client.common.pb.SqlQueryResponse other) {
      if (other == com.taobao.search.iquan.client.common.pb.SqlQueryResponse.getDefaultInstance()) return this;
      if (other.getErrorCode() != 0) {
        setErrorCode(other.getErrorCode());
      }
      if (!other.getErrorMessage().isEmpty()) {
        errorMessage_ = other.errorMessage_;
        onChanged();
      }
      internalGetMutableDebugInfos().mergeFrom(
          other.internalGetDebugInfos());
      if (other.hasResult()) {
        mergeResult(other.getResult());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.taobao.search.iquan.client.common.pb.SqlQueryResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.taobao.search.iquan.client.common.pb.SqlQueryResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int errorCode_ ;
    /**
     * <code>int32 error_code = 1;</code>
     */
    public int getErrorCode() {
      return errorCode_;
    }
    /**
     * <code>int32 error_code = 1;</code>
     */
    public Builder setErrorCode(int value) {
      
      errorCode_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 error_code = 1;</code>
     */
    public Builder clearErrorCode() {
      
      errorCode_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object errorMessage_ = "";
    /**
     * <code>string error_message = 2;</code>
     */
    public java.lang.String getErrorMessage() {
      java.lang.Object ref = errorMessage_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        errorMessage_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string error_message = 2;</code>
     */
    public com.google.protobuf.ByteString
        getErrorMessageBytes() {
      java.lang.Object ref = errorMessage_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        errorMessage_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string error_message = 2;</code>
     */
    public Builder setErrorMessage(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      errorMessage_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string error_message = 2;</code>
     */
    public Builder clearErrorMessage() {
      
      errorMessage_ = getDefaultInstance().getErrorMessage();
      onChanged();
      return this;
    }
    /**
     * <code>string error_message = 2;</code>
     */
    public Builder setErrorMessageBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      errorMessage_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.MapField<
        java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue> debugInfos_;
    private com.google.protobuf.MapField<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue>
    internalGetDebugInfos() {
      if (debugInfos_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            DebugInfosDefaultEntryHolder.defaultEntry);
      }
      return debugInfos_;
    }
    private com.google.protobuf.MapField<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue>
    internalGetMutableDebugInfos() {
      onChanged();;
      if (debugInfos_ == null) {
        debugInfos_ = com.google.protobuf.MapField.newMapField(
            DebugInfosDefaultEntryHolder.defaultEntry);
      }
      if (!debugInfos_.isMutable()) {
        debugInfos_ = debugInfos_.copy();
      }
      return debugInfos_;
    }

    public int getDebugInfosCount() {
      return internalGetDebugInfos().getMap().size();
    }
    /**
     * <code>map&lt;string, .iquan.pb.AnyValue&gt; debug_infos = 3;</code>
     */

    public boolean containsDebugInfos(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      return internalGetDebugInfos().getMap().containsKey(key);
    }
    /**
     * Use {@link #getDebugInfosMap()} instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue> getDebugInfos() {
      return getDebugInfosMap();
    }
    /**
     * <code>map&lt;string, .iquan.pb.AnyValue&gt; debug_infos = 3;</code>
     */

    public java.util.Map<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue> getDebugInfosMap() {
      return internalGetDebugInfos().getMap();
    }
    /**
     * <code>map&lt;string, .iquan.pb.AnyValue&gt; debug_infos = 3;</code>
     */

    public com.taobao.search.iquan.client.common.pb.AnyValue getDebugInfosOrDefault(
        java.lang.String key,
        com.taobao.search.iquan.client.common.pb.AnyValue defaultValue) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue> map =
          internalGetDebugInfos().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;string, .iquan.pb.AnyValue&gt; debug_infos = 3;</code>
     */

    public com.taobao.search.iquan.client.common.pb.AnyValue getDebugInfosOrThrow(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue> map =
          internalGetDebugInfos().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }

    public Builder clearDebugInfos() {
      internalGetMutableDebugInfos().getMutableMap()
          .clear();
      return this;
    }
    /**
     * <code>map&lt;string, .iquan.pb.AnyValue&gt; debug_infos = 3;</code>
     */

    public Builder removeDebugInfos(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      internalGetMutableDebugInfos().getMutableMap()
          .remove(key);
      return this;
    }
    /**
     * Use alternate mutation accessors instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue>
    getMutableDebugInfos() {
      return internalGetMutableDebugInfos().getMutableMap();
    }
    /**
     * <code>map&lt;string, .iquan.pb.AnyValue&gt; debug_infos = 3;</code>
     */
    public Builder putDebugInfos(
        java.lang.String key,
        com.taobao.search.iquan.client.common.pb.AnyValue value) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      if (value == null) { throw new java.lang.NullPointerException(); }
      internalGetMutableDebugInfos().getMutableMap()
          .put(key, value);
      return this;
    }
    /**
     * <code>map&lt;string, .iquan.pb.AnyValue&gt; debug_infos = 3;</code>
     */

    public Builder putAllDebugInfos(
        java.util.Map<java.lang.String, com.taobao.search.iquan.client.common.pb.AnyValue> values) {
      internalGetMutableDebugInfos().getMutableMap()
          .putAll(values);
      return this;
    }

    private com.taobao.search.iquan.client.common.pb.SqlPlan result_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.taobao.search.iquan.client.common.pb.SqlPlan, com.taobao.search.iquan.client.common.pb.SqlPlan.Builder, com.taobao.search.iquan.client.common.pb.SqlPlanOrBuilder> resultBuilder_;
    /**
     * <code>.iquan.pb.SqlPlan result = 4;</code>
     */
    public boolean hasResult() {
      return resultBuilder_ != null || result_ != null;
    }
    /**
     * <code>.iquan.pb.SqlPlan result = 4;</code>
     */
    public com.taobao.search.iquan.client.common.pb.SqlPlan getResult() {
      if (resultBuilder_ == null) {
        return result_ == null ? com.taobao.search.iquan.client.common.pb.SqlPlan.getDefaultInstance() : result_;
      } else {
        return resultBuilder_.getMessage();
      }
    }
    /**
     * <code>.iquan.pb.SqlPlan result = 4;</code>
     */
    public Builder setResult(com.taobao.search.iquan.client.common.pb.SqlPlan value) {
      if (resultBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        result_ = value;
        onChanged();
      } else {
        resultBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.iquan.pb.SqlPlan result = 4;</code>
     */
    public Builder setResult(
        com.taobao.search.iquan.client.common.pb.SqlPlan.Builder builderForValue) {
      if (resultBuilder_ == null) {
        result_ = builderForValue.build();
        onChanged();
      } else {
        resultBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.iquan.pb.SqlPlan result = 4;</code>
     */
    public Builder mergeResult(com.taobao.search.iquan.client.common.pb.SqlPlan value) {
      if (resultBuilder_ == null) {
        if (result_ != null) {
          result_ =
            com.taobao.search.iquan.client.common.pb.SqlPlan.newBuilder(result_).mergeFrom(value).buildPartial();
        } else {
          result_ = value;
        }
        onChanged();
      } else {
        resultBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.iquan.pb.SqlPlan result = 4;</code>
     */
    public Builder clearResult() {
      if (resultBuilder_ == null) {
        result_ = null;
        onChanged();
      } else {
        result_ = null;
        resultBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.iquan.pb.SqlPlan result = 4;</code>
     */
    public com.taobao.search.iquan.client.common.pb.SqlPlan.Builder getResultBuilder() {
      
      onChanged();
      return getResultFieldBuilder().getBuilder();
    }
    /**
     * <code>.iquan.pb.SqlPlan result = 4;</code>
     */
    public com.taobao.search.iquan.client.common.pb.SqlPlanOrBuilder getResultOrBuilder() {
      if (resultBuilder_ != null) {
        return resultBuilder_.getMessageOrBuilder();
      } else {
        return result_ == null ?
            com.taobao.search.iquan.client.common.pb.SqlPlan.getDefaultInstance() : result_;
      }
    }
    /**
     * <code>.iquan.pb.SqlPlan result = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.taobao.search.iquan.client.common.pb.SqlPlan, com.taobao.search.iquan.client.common.pb.SqlPlan.Builder, com.taobao.search.iquan.client.common.pb.SqlPlanOrBuilder> 
        getResultFieldBuilder() {
      if (resultBuilder_ == null) {
        resultBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.taobao.search.iquan.client.common.pb.SqlPlan, com.taobao.search.iquan.client.common.pb.SqlPlan.Builder, com.taobao.search.iquan.client.common.pb.SqlPlanOrBuilder>(
                getResult(),
                getParentForChildren(),
                isClean());
        result_ = null;
      }
      return resultBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:iquan.pb.SqlQueryResponse)
  }

  // @@protoc_insertion_point(class_scope:iquan.pb.SqlQueryResponse)
  private static final com.taobao.search.iquan.client.common.pb.SqlQueryResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.taobao.search.iquan.client.common.pb.SqlQueryResponse();
  }

  public static com.taobao.search.iquan.client.common.pb.SqlQueryResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SqlQueryResponse>
      PARSER = new com.google.protobuf.AbstractParser<SqlQueryResponse>() {
    @java.lang.Override
    public SqlQueryResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new SqlQueryResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SqlQueryResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SqlQueryResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.taobao.search.iquan.client.common.pb.SqlQueryResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

