package com.touna.leeo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;



public class JEmailUtils {
	/**
	 *  发件人的邮箱
	 */
    private String senderEmailAccount;
    /**
     * 发件人的密码（替换为自己的邮箱和密码）
     */
    private String senderEmailPassword;
	/**
	 * 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般格式为: smtp.xxx.com,例如:smtp.exmail.qq.com(人人聚财个人邮箱SMTP服务器)
	 *  网易163邮箱的 SMTP 服务器地址为: smtp.163.com
	 */
    private String senderEmailSMTPHost;
	/**
	 *  收件人邮箱(替换为自己知道的有效邮箱,例如:lizehua@rrjc.com)
	 */
    private String receiveMailAccount;
    
    /**
     *  收件人邮箱列表
     */
    private List<String> receiveMailAccountList;
    
    /**
     * 抄送人邮箱地址
     */
    private String ccAddress;
    /**
     * 抄送给多人邮箱地址列表 
     */
    private List<String> ccAddressList;
    
    /**
     * 密送人邮箱列表
     */
    private List<String> bccAddressList;
    
    /**
     * 邮件主题
     */
    private String subject;
    
    /**
     * 待发送的邮件内容
     */
    private String toSendContent;	
    
    /**
     *待发送的内嵌图片,图片为绝对路径 
     */
    private String toSendImage;
    /**
     *待发送的内嵌图片集合,每张图片为绝对路径 
     */
    private List<String> toSendImageList;
     
    /**
     * 待发送单个附件
     */
    private String attachment;
    /**
     * 待发送附件列表
     */
    private List<String> attachmentList;
    
    public JEmailUtils(){}
   
    /** 邮件工具构造器,收件人可以通过字段receiveMailAccount或receiveMailAccountList进行设置
     * @param senderEmailAccount	发件人邮箱账号 
     * @param senderEmailPassword	发件人邮箱密码
     * @param senderEmailSMTPHost	发件人SMTP服务器地址
     */
    public JEmailUtils(String senderEmailAccount,String senderEmailPassword,String senderEmailSMTPHost){
    	this.senderEmailAccount = senderEmailAccount;
    	this.senderEmailPassword = senderEmailPassword;
    	this.senderEmailSMTPHost = senderEmailSMTPHost;
    }
    /**
     * @param senderEmailAccount	发件人邮箱账号 
     * @param senderEmailPassword	发件人邮箱密码
     * @param senderEmailSMTPHost	发件人SMTP服务器地址
     * @param receiveMailAccount	收件人账户
     */
    public JEmailUtils(String senderEmailAccount,String senderEmailPassword,String senderEmailSMTPHost,String receiveMailAccount){
    	this.senderEmailAccount = senderEmailAccount;
    	this.senderEmailPassword = senderEmailPassword;
    	this.senderEmailSMTPHost = senderEmailSMTPHost;
    	this.receiveMailAccount = receiveMailAccount;
    }
    /**
     * @param senderEmailAccount		发件人邮箱账号 
     * @param senderEmailPassword		发件人邮箱密码
     * @param senderEmailSMTPHost		发件人SMTP服务器地址
     * @param receiveMailAccountList	收件人账户列表
     */
    public JEmailUtils(String senderEmailAccount,String senderEmailPassword,String senderEmailSMTPHost, List<String> receiveMailAccountList){
    	this.senderEmailAccount = senderEmailAccount;
    	this.senderEmailPassword = senderEmailPassword;
    	this.senderEmailSMTPHost = senderEmailSMTPHost;
    	this.receiveMailAccountList = receiveMailAccountList;
    }
    
    public String getSenderEmailAccount() {
		return senderEmailAccount;
	}
	public void setSenderEmailAccount(String senderEmailAccount) {
		this.senderEmailAccount = senderEmailAccount;
	}
	public String getSenderEmailPassword() {
		return senderEmailPassword;
	}
	public void setSenderEmailPassword(String senderEmailPassword) {
		this.senderEmailPassword = senderEmailPassword;
	}
    public String getSenderEmailSMTPHost() {
		return senderEmailSMTPHost;
	}
	public void setSenderEmailSMTPHost(String senderEmailSMTPHost) {
		this.senderEmailSMTPHost = senderEmailSMTPHost;
	}
	public String getReceiveMailAccount() {
		return receiveMailAccount;
	}
	public void setReceiveMailAccount(String receiveMailAccount) {
		this.receiveMailAccount = receiveMailAccount;
	}
	
	public List<String> getReceiveMailAccountList() {
		return receiveMailAccountList;
	}
	public void setReceiveMailAccountList(List<String> receiveMailAccountList) {
		this.receiveMailAccountList = receiveMailAccountList;
	}
	/**
	 * 添加收信人
	 * @param receive
	 */
	public void addReceive(String receive){
		if(StringUtils.isEmpty(receive)){
			return;
		}
		if(null == this.receiveMailAccountList){
			this.receiveMailAccountList = new ArrayList<String>();
		}
		this.receiveMailAccountList.add(receive);
	}
	
	public String getToSendContent() {
		return toSendContent;
	}
	public void setToSendContent(String toSendContent) {
		this.toSendContent = toSendContent;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getToSendImage() {
		return toSendImage;
	}

	public void setToSendImage(String toSendImage) {
		this.toSendImage = toSendImage;
	}

	public List<String> getToSendImageList() {
		return toSendImageList;
	}

	public void setToSendImageList(List<String> toSendImageList) {
		this.toSendImageList = toSendImageList;
	}
	/**
	 * 添加待发送内嵌图片
	 * @param toImage
	 */
	public void addToSendImage(String toImage){
		if(StringUtils.isEmpty(toImage)){
			return;
		}
		if(null == this.toSendImageList){
			this.toSendImageList = new ArrayList<String>();
		}
		this.toSendImageList.add(toImage);
	}
	
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public List<String> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<String> attachmentList) {
		this.attachmentList = attachmentList;
	}
	
	/**
	 * 添加附件
	 * @param attach
	 */
	public void addAttachment(String attach){
		if(StringUtils.isEmpty(attach)){
			return;
		}
		
		if(null == this.attachmentList){
			this.attachmentList = new ArrayList<String>();
		}
		this.attachmentList.add(attach);
	}
	
	public String getCcAddress() {
		return ccAddress;
	}

	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}

	public List<String> getCcAddressList() {
		return ccAddressList;
	}

	public void setCcAddressList(List<String> ccAddressList) {
		this.ccAddressList = ccAddressList;
	}
	/**
	 * 添加抄送
	 * @param cc 抄送者邮箱
	 */
	public void addCcAddress(String  cc){
		if(StringUtils.isEmpty(cc)){
			return;
		}
		if(null == this.ccAddress){
			this.ccAddressList = new ArrayList<String>();
		}
		this.ccAddressList.add(cc);
	}
	public List<String> getBccAddressList() {
		return bccAddressList;
	}
	public void setBccAddressList(List<String> bccAddressList) {
		this.bccAddressList = bccAddressList;
	}
	/**
	 * 添加密送人
	 * @param bcc
	 */
	public void addBccAddress(String bcc){
		if(StringUtils.isEmpty(bcc)){
			return;
		}
		if(null == this.ccAddress){
			this.bccAddressList = new ArrayList<String>();
		}
		this.bccAddressList.add(bcc);
	}
	public static void main(String[] args) throws Exception {
		JEmailUtils email = new JEmailUtils("lizehua@rrjc.com", "Rrjc123", "smtp.exmail.qq.com", "shuzhi_lan@163.com");
		email.setSubject("happy image");
		List<String> img = new ArrayList<String>();
		img.add("f:/images/gc_05_week20161213.png");
		img.add("f:/images/pp_06_week20161213.png");
		img.add("f:/images/rrw_01_week20161213.png");
		
		System.out.println(UUID.randomUUID().toString());
		
		
		
	}
	
	/**
	 * 发送简单的邮件内容
	 * 发送附件内容通过设置attachment或attachmentList字段值
	 * @throws Exception
	 * @throws NoSuchProviderException
	 * @throws MessagingException
	 */
	public void sendContentMail() throws Exception,
			NoSuchProviderException, MessagingException {
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = getSession(true);
	    // 3. 创建一封邮件
	    MimeMessage message = createContentMimeMessage(session);
	    // 4. 根据 Session 获取邮件传输对象
	    Transport transport = session.getTransport();
	    // 5. 使用 邮箱账号 和 密码 连接邮件服务器
	    //    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
	    transport.connect(getSenderEmailAccount(), getSenderEmailPassword());
	    // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
	    transport.sendMessage(message, message.getAllRecipients());
	    // 7. 关闭连接
	    transport.close();
	}
	
	
	
	/**
	 * 发送图文邮件,图片是嵌套在邮件内容内,不是作为附件.允许有多张图片,文字在上,图片邮件文字下方显示
	 * 多张图片通过 toSendImageList参数进行设置
	 * 发送附件内容通过设置attachment或attachmentList字段值
	 * @throws Exception
	 * @throws NoSuchProviderException
	 * @throws MessagingException
	 */
	public void sendContentImgMail() throws Exception,
	NoSuchProviderException, MessagingException {
		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = getSession(true);
		// 3. 创建一封邮件
		MimeMessage message = createTextImgMimeMessage(session);
		// 4. 根据 Session 获取邮件传输对象
		Transport transport = session.getTransport();
		// 5. 使用 邮箱账号 和 密码 连接邮件服务器
		//    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
		transport.connect(getSenderEmailAccount(), getSenderEmailPassword());
		// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
		transport.sendMessage(message, message.getAllRecipients());
		// 7. 关闭连接
		transport.close();
	}
	
	
	public void sendBindTextImageMail(String content,List<String> imagePath) throws Exception{
		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = getSession(true);
		// 3. 创建一封邮件
		MimeMessage message = bindTextImg(content, imagePath, session);
		// 4. 根据 Session 获取邮件传输对象
		Transport transport = session.getTransport();
		// 5. 使用 邮箱账号 和 密码 连接邮件服务器
		//    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
		transport.connect(getSenderEmailAccount(), getSenderEmailPassword());
		// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
		transport.sendMessage(message, message.getAllRecipients());
		// 7. 关闭连接
		transport.close();
	}
	
	
	
	
	/**
	 * 获取参数配置
	 * @return
	 */
	public Properties getProperties() {
		Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.host", senderEmailSMTPHost);        // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");
		return props;
	}
	
	/**
	 * 创建一封只包含文本的简单邮件
	 * 发送附件内容通过设置attachment或attachmentList字段值
     * @param session 和服务器交互的会话
	 * @return MimeMessage
	 * @throws Exception
	 */
    public MimeMessage createContentMimeMessage(Session session) throws Exception {
        MimeMessage message = createMimeMessage(session);
        
        // 可以装载多个主体部件！可以把它当成是一个集合
        MimeMultipart partList = new MimeMultipart();
        message.setContent(partList);// 把邮件的内容设置为多部件的集合对象
        // 创建一个部件
        MimeBodyPart part1 = new MimeBodyPart();
        
        // 5. Content: 邮件正文（可以使用html标签）
        if(StringUtils.isNotEmpty(getToSendContent())){
        	part1.setContent(getToSendContent(), "text/html;charset=utf-8");
        }
        partList.addBodyPart(part1);

        if(StringUtils.isNotEmpty(getAttachment())||(null != getAttachmentList() && getAttachmentList().size() > 0)){
        	  // 又创建一个部件
             MimeBodyPart part2 = new MimeBodyPart();
        	 if(StringUtils.isNotEmpty(getAttachment())){
             	File file = new File(getAttachment());
				part2.attachFile(file);
             	part2.setFileName(MimeUtility.encodeText(file.getName()));
             	partList.addBodyPart(part2);
             }
             if((null != getAttachmentList() && getAttachmentList().size() > 0)){
             	for (String attach : attachmentList) {
             		part2 = new MimeBodyPart();
             		File file = new File(attach);
    				part2.attachFile(file);
                 	part2.setFileName(MimeUtility.encodeText(file.getName()));
                 	partList.addBodyPart(part2);
 				}
             }
        }
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }

    
    /**
     * 创建一封复杂邮件(文本+内嵌图片)
     * 发送附件内容通过设置attachment或attachmentList字段值
     */
    public MimeMessage createTextImgMimeMessage(Session session) throws Exception {
        MimeMessage message = createMimeMessage(session);
        // 7. 创建图片“节点”
        int  image_index  = 0;
        // 7. （文本+图片）设置 文本 和 图片 “节点”的关系（将 文本 和 图片 “节点”合成一个混合“节点”）
        MimeMultipart mm_text_image = new MimeMultipart();
        
        //8. 创建文本“节点”
        MimeBodyPart text = new MimeBodyPart();
        mm_text_image.addBodyPart(text);
        String imageId = null;
        StringBuffer buffer = new StringBuffer();
        buffer.append(getToSendContent());
        if(StringUtils.isNotEmpty(getToSendImage())){
        	image_index ++;
            MimeBodyPart image = new MimeBodyPart();
            DataHandler dh = new DataHandler(new FileDataSource(new File(getToSendImage()))); // 读取本地文件
            image.setDataHandler(dh);                   // 将图片数据添加到“节点”
            imageId = "image_" + image_index;
            image.setContentID(imageId);     // 为“节点”设置一个唯一编号（在文本“节点”将引用该ID）
            //这里添加图片的方式是将整个图片包含到邮件内容中, 实际上也可以以 http 链接的形式添加网络图片
            buffer.append("<img src='cid:").append(imageId).append("'/>");
            mm_text_image.addBodyPart(image);
        }
        
        if(null != getToSendImageList() && getToSendImageList().size() > 0){
        	MimeBodyPart image = null;
        	DataHandler dh = null;
        	for (String img : getToSendImageList()) {
        		image_index ++;
        		image = new MimeBodyPart();
        		dh = new DataHandler(new FileDataSource(new File(img))); // 读取本地文件
        		image.setDataHandler(dh);                   // 将图片数据添加到“节点”
        		imageId = "image_" + image_index;
        		image.setContentID(imageId);     // 为“节点”设置一个唯一编号（在文本“节点”将引用该ID）
        		buffer.append("<img src='cid:").append(imageId).append("'/>");
        		mm_text_image.addBodyPart(image);
			}
        }
        text.setContent(buffer.toString(),"text/html;charset=UTF-8");
        mm_text_image.setSubType("related");    // 关联关系
        
        if(StringUtils.isNotEmpty(getAttachment())||(null != getAttachmentList() && getAttachmentList().size() > 0)){
        	 // 9. 将 文本+图片 的混合“节点”封装成一个普通“节点”
            //    最终添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是 BodyPart,
            //    上面的 mm_text_image 并非 BodyPart, 所有要把 mm_text_image 封装成一个 BodyPart
            MimeBodyPart text_image = new MimeBodyPart();
            text_image.setContent(mm_text_image);
            // 10. 设置（文本+图片）和 附件 的关系（合成一个大的混合“节点” / Multipart ）
            MimeMultipart mm = new MimeMultipart();
            mm.addBodyPart(text_image);
            // 11. 创建附件“节点”
            if(StringUtils.isNotEmpty(getAttachment())){
            	MimeBodyPart attachment = new MimeBodyPart();
            	DataHandler dh = new DataHandler(new FileDataSource(getAttachment()));  // 读取本地文件
            	attachment.setDataHandler(dh);                                             // 将附件数据添加到“节点”
            	attachment.setFileName(MimeUtility.encodeText(dh.getName()));              // 设置附件的文件名（需要编码）
            	mm.addBodyPart(attachment);     // 如果有多个附件，可以创建多个多次添加
            }
            if((null != getAttachmentList() && getAttachmentList().size() > 0)){
            	for (String attach : attachmentList) {
            		MimeBodyPart attachment = new MimeBodyPart();
            		DataHandler dh = new DataHandler(new FileDataSource(attach));  // 读取本地文件
            		attachment.setDataHandler(dh);                                             // 将附件数据添加到“节点”
            		attachment.setFileName(MimeUtility.encodeText(dh.getName()));              // 设置附件的文件名（需要编码）
            		mm.addBodyPart(attachment);     // 如果有多个附件，可以创建多个多次添加
				}
            }
            mm.setSubType("mixed");         // 混合关系
         // 12. 设置整个邮件的关系（将最终的混合“节点”作为邮件的内容添加到邮件对象）
            message.setContent(mm);
        }else{
        	message.setContent(mm_text_image);
        }
        // 13. 设置发件时间
        message.setSentDate(new Date());
        // 14. 保存上面的所有设置
        message.saveChanges();
        return message;
    }
    
    
    
    
    /**
     * 绑定图文
     * @param content
     * @param imagePath
     * @param session
     * @throws Exception
     */
    public MimeMessage bindTextImg(String content,List<String> imagePath,Session session) throws Exception{
    	MimeMessage message = createMimeMessage(session);
        // 7. 创建图片“节点”
        // 7. （文本+图片）设置 文本 和 图片 “节点”的关系（将 文本 和 图片 “节点”合成一个混合“节点”）
        MimeMultipart mm_text_image = new MimeMultipart();
        
        //8. 创建文本“节点”
        MimeBodyPart text = new MimeBodyPart();
        
        
        
        mm_text_image.addBodyPart(text);
        String imageId = null;
        
        StringBuffer buffer = new StringBuffer();	//文字
        buffer.append(content);
        
        int  image_index  = 0;
        if(null != imagePath && imagePath.size() > 0){
        	for (String img : imagePath) {
        		MimeBodyPart image = new MimeBodyPart();	//图片
        		DataHandler dh = new DataHandler(new FileDataSource(new File(img))); // 读取本地文件
                image.setDataHandler(dh);                   // 将图片数据添加到“节点”
                imageId = "image_" + (image_index++);
                image.setContentID(imageId);     // 为“节点”设置一个唯一编号（在文本“节点”将引用该ID）
                mm_text_image.addBodyPart(image);
                buffer.append("<img src='cid:").append(imageId).append("'/>");
			}
        }
        text.setContent(buffer.toString(),"text/html;charset=UTF-8");
        mm_text_image.setSubType("related");    // 关联关系
        
        mm_text_image.addBodyPart(text);
       
        message.setContent(mm_text_image);
        
        // 13. 设置发件时间
        message.setSentDate(new Date());
        // 14. 保存上面的所有设置
        message.saveChanges();
        return message;
    }

    
    
    /**
     * 添加图文节点
     * @param content
     * @param imagePath
     * @param session
     * @throws Exception
     */
    public MimeMultipart addTextImgNode(String content,List<String> imagePath,MimeMultipart mm_text_image,Session session) throws Exception{
    	//创建文本“节点”
    	MimeBodyPart text = new MimeBodyPart();
    	mm_text_image.addBodyPart(text);
    	String imageId = null;
    	
    	StringBuffer buffer = new StringBuffer();	//文字
    	buffer.append(content);
    	
    	if(null != imagePath && imagePath.size() > 0){
    		for (String img : imagePath) {
    			MimeBodyPart image = new MimeBodyPart();	//图片
    			DataHandler dh = new DataHandler(new FileDataSource(new File(img))); // 读取本地文件
    			image.setDataHandler(dh);                   // 将图片数据添加到“节点”
    			imageId = "image_" + (UUID.randomUUID().toString());
    			image.setContentID(imageId);     // 为“节点”设置一个唯一编号（在文本“节点”将引用该ID）
    			mm_text_image.addBodyPart(image);
    			buffer.append("<img src='cid:").append(imageId).append("'/>");
    		}
    	}
    	text.setContent(buffer.toString(),"text/html;charset=UTF-8");
    	mm_text_image.setSubType("related");    // 关联关系
    	return mm_text_image;
    }
    
    
    
    /**
     * 添加图文节点
     * @param content
     * @param imagePath
     * @param session
     * @throws Exception
     */
    public MimeMultipart addTextImgNode(String content,String[] imagePath,MimeMultipart mm_text_image,Session session) throws Exception{
    	//创建文本“节点”
    	MimeBodyPart text = new MimeBodyPart();
    	
    	mm_text_image.addBodyPart(text);
    	String imageId = null;
    	
    	StringBuffer buffer = new StringBuffer();	//文字
    	buffer.append(content);
    	
    	if(null != imagePath && imagePath.length > 0){
    		for (String img : imagePath) {
    			MimeBodyPart image = new MimeBodyPart();	//图片
    			DataHandler dh = new DataHandler(new FileDataSource(new File(img))); // 读取本地文件
    			image.setDataHandler(dh);                   // 将图片数据添加到“节点”
    			imageId = "image_" + (UUID.randomUUID().toString());
    			image.setContentID(imageId);     // 为“节点”设置一个唯一编号（在文本“节点”将引用该ID）
    			mm_text_image.addBodyPart(image);
    			buffer.append("<img src='cid:").append(imageId).append("'/>");
    		}
    	}
    	text.setContent(buffer.toString(),"text/html;charset=UTF-8");
    	mm_text_image.setSubType("related");    // 关联关系
    	return mm_text_image;
    }
    /**
     * 添加图文节点
     * @param content
     * @param imagePath
     * @param session
     * @throws Exception
     */
    public MimeMultipart addTextImgNode2(String content,String[] imagePath,MimeMultipart mm_text_image,Session session) throws Exception{
    	//创建文本“节点”
    	MimeBodyPart text = new MimeBodyPart();
    	
    	text.setContent(mm_text_image);
    	
    	mm_text_image.addBodyPart(text);
    	StringBuffer buffer = new StringBuffer();	//文字
    	buffer.append(content);
    	
    	if(null != imagePath && imagePath.length > 0){
    		for (String img : imagePath) {
				byte[] data = null;
				File file = new File(img);
				if(file.exists()){
					FileInputStream input = new FileInputStream(file);
					try{
						data = new byte[input.available()];
						input.read(data);
						input.close();
						buffer.append("<img src='data:image/png;base64,").append(Encodes.encodeBase64(data)).append("'/>");
					}catch(Exception ex){
						if(null != input){
							input.close();
						}
						throw new RuntimeException(ex);
					}
				}
    		}
    	}
    	text.setContent(buffer.toString(),"text/html;charset=UTF-8");
    	mm_text_image.setSubType("related");    // 关联关系
    	return mm_text_image;
    }
    
    
    /**
     * 创建邮件
     * @param session
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
	public MimeMessage createMimeMessage(Session session)
			throws MessagingException, UnsupportedEncodingException {
		// 1. 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人
        message.setFrom(new InternetAddress(getSenderEmailAccount(), null, "UTF-8"));
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        if(StringUtils.isNotEmpty(getReceiveMailAccount())){
        	message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(getReceiveMailAccount(), null, "UTF-8"));
        }
        if(getReceiveMailAccountList() != null && getReceiveMailAccountList().size() > 0){
        	for (String recipient : getReceiveMailAccountList()) {
        		message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipient, null, "UTF-8"));
			}
        }
        //4.添加抄送
        if(StringUtils.isNotEmpty(getCcAddress())){
        	message.addRecipient(MimeMessage.RecipientType.CC,  new InternetAddress(getCcAddress(), null, "UTF-8"));
        }
        if(getCcAddressList() != null && getCcAddressList().size() > 0){
        	for (String cc : getCcAddressList()) {
        		message.addRecipient(MimeMessage.RecipientType.CC, new InternetAddress(cc, null, "UTF-8"));
			}
        }
        //5.添加密送
        if(getBccAddressList() != null  && getBccAddressList().size() > 0){
        	for (String bcc : getBccAddressList()) {
        		message.addRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(bcc, null, "UTF-8"));
			}
        }
        // 6. Subject: 邮件主题
        if(StringUtils.isNotEmpty(getSubject())){
        	message.setSubject(getSubject(), "UTF-8");
        }
		return message;
	}
    
    
    
    
	public Session getSession(boolean debug) {
		Session session = Session.getDefaultInstance(getProperties());
        session.setDebug(debug);
		return session;
	}
}
