import clsx from 'clsx';
import Heading from '@theme/Heading';
import styles from './styles.module.css';

type FeatureItem = {
  title: string;
  Svg: React.ComponentType<React.ComponentProps<'svg'>>;
  description: JSX.Element;
};

const FeatureList: FeatureItem[] = [
  {
    title: '客户端SDK支持',
    Svg: require('@site/static/img/idea.svg').default,
    description: (
      <>
          基于SpringBootStarter的SDK实现, 只需要入依赖&配置AK/SK,一行代码即可实现接口调用。
      </>
    ),
  },
  {
    title: 'AIGC集成',
    Svg: require('@site/static/img/aigc.svg').default,
    description: (
      <>
        接口模块集成了各个模型客户端, 祝您实现更加前沿, 便捷的接口调用体验。
      </>
    ),
  },
  {
    title: '分布式部署',
    Svg: require('@site/static/img/disturb.svg').default,
    description: (
      <>
        通过微服务以及注册中心和接口模块分布式部署充分保证接口服务的可用性。
      </>
    ),
  },
];

function Feature({title, Svg, description}: FeatureItem) {
  return (
    <div className={clsx('col col--4')}>
      <div className="text--center">
        <Svg className={styles.featureSvg} role="img" />
      </div>
      <div className="text--center padding-horiz--md">
        <Heading as="h3">{title}</Heading>
        <p>{description}</p>
      </div>
    </div>
  );
}

export default function HomepageFeatures(): JSX.Element {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          {FeatureList.map((props, idx) => (
            <Feature key={idx} {...props} />
          ))}
        </div>
      </div>
    </section>
  );
}
