package laz.plasmine.client.models.block.miner;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import laz.plasmine.client.models.block.ITileModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.Direction;

public class MinerModel extends Model implements ITileModel{
	private final ModelRenderer bone;
	private final ModelRenderer bone4;
	private final ModelRenderer bone3;
	private final ModelRenderer bone2;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;

	public MinerModel() {
		super(RenderType::getEntityTranslucentCull);
		textureWidth = 256;
		textureHeight = 256;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.125F, 18.8125F, 11.0F);
		setRotationAngle(bone, 0.0F, -1.5708F, 0.0F);
		bone.setTextureOffset(47, 142).addBox(-20.125F, -0.8125F, 8.0F, 16.0F, 6.0F, 15.0F, 0.0F, false);
		bone.setTextureOffset(0, 136).addBox(-20.125F, -0.8125F, -23.0F, 16.0F, 6.0F, 15.0F, 0.0F, false);
		bone.setTextureOffset(62, 108).addBox(10.875F, -13.8125F, -23.0F, 16.0F, 19.0F, 15.0F, 0.0F, false);
		bone.setTextureOffset(112, 24).addBox(10.875F, -11.8125F, 8.0F, 16.0F, 17.0F, 15.0F, 0.0F, false);
		bone.setTextureOffset(0, 0).addBox(-9.125F, -26.8125F, -14.0F, 28.0F, 31.0F, 28.0F, 0.0F, false);
		bone.setTextureOffset(0, 59).addBox(0.875F, -10.8125F, -8.0F, 27.0F, 16.0F, 16.0F, 0.0F, false);
		bone.setTextureOffset(0, 91).addBox(5.875F, -14.8125F, -17.0F, 6.0F, 21.0F, 3.0F, 0.0F, false);
		bone.setTextureOffset(0, 0).addBox(5.875F, -14.8125F, 14.0F, 6.0F, 21.0F, 3.0F, 0.0F, false);
		bone.setTextureOffset(124, 124).addBox(-19.125F, -6.8125F, -8.0F, 15.0F, 12.0F, 16.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(4.875F, -12.4741F, -19.1486F);
		bone.addChild(bone4);
		setRotationAngle(bone4, -0.4363F, 0.0F, 0.0F);
		bone4.setTextureOffset(84, 0).addBox(-15.0F, -14.8384F, -1.8514F, 30.0F, 17.0F, 7.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(4.875F, -17.8125F, 15.0943F);
		bone.addChild(bone3);
		setRotationAngle(bone3, 0.4363F, 0.0F, 0.0F);
		bone3.setTextureOffset(100, 59).addBox(-15.0F, -8.5F, -3.5F, 30.0F, 17.0F, 7.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-7.625F, -7.3125F, 0.0F);
		bone.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.4363F);
		bone2.setTextureOffset(109, 152).addBox(-6.5F, -10.5F, -17.0F, 13.0F, 21.0F, 3.0F, 0.0F, false);
		bone2.setTextureOffset(141, 152).addBox(-6.5F, -10.5F, 14.0F, 13.0F, 21.0F, 3.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-19.8399F, -18.3125F, 0.0F);
		bone.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 0.0F, 0.4363F);
		bone5.setTextureOffset(60, 65).addBox(6.071F, -9.6997F, -13.0F, 7.0F, 17.0F, 26.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(-11.8399F, -18.3125F, 0.0F);
		bone.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, 0.4363F);
		bone6.setTextureOffset(0, 91).addBox(-1.4531F, -3.667F, -14.0F, 3.0F, 17.0F, 28.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bone.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void rotate(Direction dir) {
		switch (dir.getIndex()) {
		case 2:
			bone.rotateAngleY = 0;
			bone.rotationPointX = 11f;
			bone.rotationPointZ = 0F;
			break;
		case 3:
			bone.rotateAngleY = (float) Math.PI;
			bone.rotationPointZ = 0f;
			bone.rotationPointX = -11F;
			break;
		case 4:
			bone.rotateAngleY = (float) Math.PI / 2;
			bone.rotationPointZ = -11;
			bone.rotationPointX = 0f;
			break;
		case 5:
			bone.rotateAngleY = -(float) Math.PI/2;
			bone.rotationPointZ = 11f;
			bone.rotationPointX = 0f;
			break;
		}
	}

	@Override
	public void animation(int tick, float partialTick) {}

	@Override
	public void resetState() {}
}